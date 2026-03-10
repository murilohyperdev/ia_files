package com.minhaempresa.meusaas.web.controller;

import com.minhaempresa.meusaas.core.entity.User;
import com.minhaempresa.meusaas.core.service.UserService;
import com.minhaempresa.meusaas.security.SecurityUtils;
import com.minhaempresa.meusaas.web.dto.request.AdminUserUpdateRequest;
import com.minhaempresa.meusaas.web.dto.request.UserCreateRequest;
import com.minhaempresa.meusaas.web.dto.response.PageResponse;
import com.minhaempresa.meusaas.web.dto.response.UserListResponse;
import com.minhaempresa.meusaas.web.dto.response.UserResponse;
import com.minhaempresa.meusaas.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Gerenciamento de usuários")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Lista todos os usuários do merchant com filtros e paginação")
    public ResponseEntity<PageResponse<UserListResponse>> findAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        UUID merchantId = SecurityUtils.getCurrentMerchantId();
        Sort sort = sortDir.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> usersPage = userService.findAllByMerchantIdWithFilters(merchantId, search, isActive, pageable);
        List<UserListResponse> content = userMapper.toListResponse(usersPage.getContent());

        return ResponseEntity.ok(PageResponse.of(usersPage, content));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário", description = "Busca um usuário pelo ID")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreateRequest request) {
        UUID merchantId = SecurityUtils.getCurrentMerchantId();

        User user = User.builder()
            .merchantId(merchantId)
            .name(request.name())
            .email(request.email())
            .passwordHash(passwordEncoder.encode(request.password()))
            .phone(request.phone())
            .isActive(true)
            .build();

        User created = userService.createWithGroups(user, request.groupIds(), merchantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário")
    public ResponseEntity<UserResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUserUpdateRequest request
    ) {
        UUID merchantId = SecurityUtils.getCurrentMerchantId();

        User userData = User.builder()
            .name(request.name())
            .email(request.email())
            .phone(request.phone())
            .isActive(request.isActive())
            .build();

        User updated = userService.updateWithGroups(id, userData, request.groupIds(), merchantId);
        return ResponseEntity.ok(userMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário", description = "Desativa um usuário (soft delete)")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/groups")
    @Operation(summary = "Atualizar grupos", description = "Atualiza os grupos de um usuário")
    public ResponseEntity<Void> updateGroups(
            @PathVariable UUID id,
            @RequestBody List<UUID> groupIds
    ) {
        UUID merchantId = SecurityUtils.getCurrentMerchantId();
        userService.updateGroups(id, groupIds, merchantId);
        return ResponseEntity.noContent().build();
    }
}
