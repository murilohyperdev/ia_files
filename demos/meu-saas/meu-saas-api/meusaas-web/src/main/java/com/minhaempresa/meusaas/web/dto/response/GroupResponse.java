package com.minhaempresa.meusaas.web.dto.response;

import java.util.UUID;

public record GroupResponse(
    UUID id,
    String name,
    String description
) {}
