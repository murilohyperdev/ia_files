package com.minhaempresa.meusaas.web.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record GroupListResponse(
    UUID id,
    String name,
    String description,
    boolean isSystem,
    boolean isActive,
    int userCount,
    LocalDateTime createdAt
) {}
