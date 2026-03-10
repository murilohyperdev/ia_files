package com.minhaempresa.meusaas.web.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GroupDetailResponse(
    UUID id,
    String name,
    String description,
    boolean isSystem,
    boolean isActive,
    int userCount,
    LocalDateTime createdAt,
    List<RuleResponse> rules
) {}
