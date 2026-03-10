package com.minhaempresa.meusaas.web.controller;

import com.minhaempresa.meusaas.core.entity.Rule;
import com.minhaempresa.meusaas.core.service.RuleService;
import com.minhaempresa.meusaas.web.dto.response.RuleResponse;
import com.minhaempresa.meusaas.web.mapper.RuleMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rules")
@RequiredArgsConstructor
@Tag(name = "Rules", description = "Gerenciamento de permissões")
@SecurityRequirement(name = "bearerAuth")
public class RuleController {

    private final RuleService ruleService;
    private final RuleMapper ruleMapper;

    @GetMapping
    @Operation(summary = "Listar permissões", description = "Lista todas as permissões disponíveis")
    public ResponseEntity<List<RuleResponse>> findAll() {
        List<Rule> rules = ruleService.findAll();
        return ResponseEntity.ok(ruleMapper.toResponse(rules));
    }

    @GetMapping("/modules")
    @Operation(summary = "Listar módulos", description = "Lista todos os módulos de permissões")
    public ResponseEntity<List<String>> findAllModules() {
        return ResponseEntity.ok(ruleService.findAllModules());
    }

    @GetMapping("/by-module/{module}")
    @Operation(summary = "Listar por módulo", description = "Lista permissões de um módulo específico")
    public ResponseEntity<List<RuleResponse>> findByModule(@PathVariable String module) {
        List<Rule> rules = ruleService.findByModule(module);
        return ResponseEntity.ok(ruleMapper.toResponse(rules));
    }
}
