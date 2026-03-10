package com.minhaempresa.meusaas.web.dto.response;

import java.util.UUID;

public record RuleResponse(
    UUID id,
    String code,
    String name,
    String description,
    String module
) {}
