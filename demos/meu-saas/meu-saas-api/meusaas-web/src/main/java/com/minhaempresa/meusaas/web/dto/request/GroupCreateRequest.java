package com.minhaempresa.meusaas.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record GroupCreateRequest(
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    String name,

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    String description,

    List<UUID> ruleIds
) {}
