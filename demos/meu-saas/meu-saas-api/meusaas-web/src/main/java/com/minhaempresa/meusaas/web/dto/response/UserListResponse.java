package com.minhaempresa.meusaas.web.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserListResponse(
    UUID id,
    String name,
    String email,
    String avatarUrl,
    String phone,
    boolean isActive,
    LocalDateTime lastLoginAt,
    LocalDateTime createdAt,
    List<GroupSimpleResponse> groups
) {}
