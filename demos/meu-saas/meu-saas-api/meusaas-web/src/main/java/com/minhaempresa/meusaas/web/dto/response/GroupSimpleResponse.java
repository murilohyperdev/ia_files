package com.minhaempresa.meusaas.web.dto.response;

import java.util.UUID;

public record GroupSimpleResponse(
    UUID id,
    String name
) {}
