package com.minhaempresa.meusaas.web.controller;

import com.minhaempresa.meusaas.core.entity.User;
import com.minhaempresa.meusaas.core.exception.BusinessException;
import com.minhaempresa.meusaas.core.service.UserService;
import com.minhaempresa.meusaas.security.SecurityUtils;
import com.minhaempresa.meusaas.web.dto.request.ChangePasswordRequest;
import com.minhaempresa.meusaas.web.dto.request.UserUpdateRequest;
import com.minhaempresa.meusaas.web.dto.response.UserResponse;
import com.minhaempresa.meusaas.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/me")
@RequiredArgsConstructor
@Tag(name = "Profile", description = "Endpoints do perfil do usuário autenticado")
@SecurityRequirement(name = "bearerAuth")
public class MeController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @Operation(summary = "Get Profile", description = "Obter dados do usuário autenticado")
    public ResponseEntity<UserResponse> getMe() {
        UUID userId = SecurityUtils.getCurrentUserId();
        User user = userService.findById(userId);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @PutMapping
    @Operation(summary = "Update Profile", description = "Atualizar dados do perfil")
    public ResponseEntity<UserResponse> updateMe(@Valid @RequestBody UserUpdateRequest request) {
        UUID userId = SecurityUtils.getCurrentUserId();
        User user = userService.findById(userId);

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPhone(request.phone());

        user = userService.update(userId, user);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @PutMapping("/password")
    @Operation(summary = "Change Password", description = "Alterar senha do usuário")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        UUID userId = SecurityUtils.getCurrentUserId();
        User user = userService.findById(userId);

        if (!passwordEncoder.matches(request.currentPassword(), user.getPasswordHash())) {
            throw new BusinessException("Senha atual incorreta");
        }

        String newPasswordHash = passwordEncoder.encode(request.newPassword());
        userService.updatePassword(userId, newPasswordHash);

        return ResponseEntity.noContent().build();
    }
}
