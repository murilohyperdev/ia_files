package com.minhaempresa.meusaas.web.controller;

import com.minhaempresa.meusaas.core.entity.*;
import com.minhaempresa.meusaas.core.exception.BusinessException;
import com.minhaempresa.meusaas.core.repository.GroupRepository;
import com.minhaempresa.meusaas.core.service.MerchantService;
import com.minhaempresa.meusaas.core.service.TokenService;
import com.minhaempresa.meusaas.core.service.UserService;
import com.minhaempresa.meusaas.security.jwt.JwtTokenProvider;
import com.minhaempresa.meusaas.web.dto.request.LoginRequest;
import com.minhaempresa.meusaas.web.dto.request.RefreshTokenRequest;
import com.minhaempresa.meusaas.web.dto.request.RegisterRequest;
import com.minhaempresa.meusaas.web.dto.response.TokenResponse;
import com.minhaempresa.meusaas.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints de autenticação")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final UserService userService;
    private final MerchantService merchantService;
    private final GroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Autenticar usuário e obter tokens")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userService.findByEmail(request.email());
        userService.updateLastLogin(user.getId());

        String accessToken = jwtTokenProvider.generateAccessToken(authentication, user.getMerchantId());
        RefreshToken refreshToken = tokenService.createRefreshToken(user.getId());

        return ResponseEntity.ok(new TokenResponse(
            accessToken,
            refreshToken.getToken(),
            jwtTokenProvider.getAccessTokenExpirationMinutes() * 60,
            userMapper.toResponse(user)
        ));
    }

    @PostMapping("/register")
    @Operation(summary = "Registro", description = "Cadastrar novo usuário")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new BusinessException("As senhas não coincidem");
        }

        if (userService.existsByEmail(request.email())) {
            throw new BusinessException("Email já cadastrado");
        }

        // Create merchant for new user
        Merchant merchant = Merchant.builder()
            .name(request.name())
            .slug(merchantService.generateSlug(request.name()))
            .email(request.email())
            .isActive(true)
            .planType("basic")
            .build();
        merchant = merchantService.create(merchant);

        // Create default admin group for merchant
        Group adminGroup = Group.builder()
            .merchantId(merchant.getId())
            .name("Administrador")
            .description("Acesso total ao sistema")
            .isSystem(true)
            .isActive(true)
            .build();
        adminGroup = groupRepository.save(adminGroup);

        // Create user
        User user = User.builder()
            .merchantId(merchant.getId())
            .name(request.name())
            .email(request.email())
            .passwordHash(passwordEncoder.encode(request.password()))
            .isActive(true)
            .groups(Set.of(adminGroup))
            .build();
        user = userService.create(user);

        // Generate tokens
        String accessToken = jwtTokenProvider.generateAccessToken(request.email(), merchant.getId());
        RefreshToken refreshToken = tokenService.createRefreshToken(user.getId());

        // Reload user with groups
        user = userService.findById(user.getId());

        return ResponseEntity.ok(new TokenResponse(
            accessToken,
            refreshToken.getToken(),
            jwtTokenProvider.getAccessTokenExpirationMinutes() * 60,
            userMapper.toResponse(user)
        ));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh Token", description = "Renovar access token usando refresh token")
    public ResponseEntity<TokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        RefreshToken refreshToken = tokenService.validateRefreshToken(request.refreshToken());
        User user = userService.findById(refreshToken.getUserId());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail(), user.getMerchantId());
        RefreshToken newRefreshToken = tokenService.createRefreshToken(user.getId());

        // Revoke old refresh token
        tokenService.revokeRefreshToken(request.refreshToken());

        return ResponseEntity.ok(new TokenResponse(
            accessToken,
            newRefreshToken.getToken(),
            jwtTokenProvider.getAccessTokenExpirationMinutes() * 60,
            userMapper.toResponse(user)
        ));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Invalidar refresh token")
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequest request) {
        tokenService.revokeRefreshToken(request.refreshToken());
        return ResponseEntity.noContent().build();
    }
}
