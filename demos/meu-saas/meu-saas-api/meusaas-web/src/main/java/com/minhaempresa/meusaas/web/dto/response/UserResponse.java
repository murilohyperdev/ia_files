package com.minhaempresa.meusaas.web.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponse(
    UUID id,
    UUID merchantId,
    String name,
    String email,
    String avatarUrl,
    String phone,
    boolean isActive,
    LocalDateTime emailVerifiedAt,
    LocalDateTime lastLoginAt,
    LocalDateTime createdAt,
    List<GroupResponse> groups,
    List<String> rules
) {}
