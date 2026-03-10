package com.minhaempresa.meusaas.web.controller;

import com.minhaempresa.meusaas.core.entity.Group;
import com.minhaempresa.meusaas.core.service.GroupService;
import com.minhaempresa.meusaas.security.SecurityUtils;
import com.minhaempresa.meusaas.web.dto.request.GroupCreateRequest;
import com.minhaempresa.meusaas.web.dto.request.GroupUpdateRequest;
import com.minhaempresa.meusaas.web.dto.response.GroupDetailResponse;
import com.minhaempresa.meusaas.web.dto.response.GroupListResponse;
import com.minhaempresa.meusaas.web.dto.response.GroupSimpleResponse;
import com.minhaempresa.meusaas.web.dto.response.PageResponse;
import com.minhaempresa.meusaas.web.mapper.GroupMapper;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
@Tag(name = "Groups", description = "Gerenciamento de grupos")
@SecurityRequirement(name = "bearerAuth")
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping
    @Operation(summary = "Listar grupos", description = "Lista todos os grupos do merchant com paginação")
    public ResponseEntity<PageResponse<GroupListResponse>> findAll(
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

        Page<Group> groupsPage = groupService.findAllByMerchantIdWithUsers(merchantId, pageable);
        List<GroupListResponse> content = groupMapper.toListResponse(groupsPage.getContent());

        return ResponseEntity.ok(PageResponse.of(groupsPage, content));
    }

    @GetMapping("/simple")
    @Operation(summary = "Listar grupos (simplificado)", description = "Lista todos os grupos para uso em select/dropdown")
    public ResponseEntity<List<GroupSimpleResponse>> findAllSimple() {
        UUID merchantId = SecurityUtils.getCurrentMerchantId();
        List<Group> groups = groupService.findAllSimple(merchantId);
        return ResponseEntity.ok(groupMapper.toSimpleResponse(groups));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar grupo", description = "Busca um grupo pelo ID")
    public ResponseEntity<GroupDetailResponse> findById(@PathVariable UUID id) {
        UUID merchantId = SecurityUtils.getCurrentMerchantId();
        Group group = groupService.findByIdWithRulesAndUsers(id, merchantId);
        return ResponseEntity.ok(groupMapper.toDetailResponse(group));
    }

    @PostMapping
    @Operation(summary = "Criar grupo", description = "Cria um novo grupo")
    public ResponseEntity<GroupDetailResponse> create(@Valid @RequestBody GroupCreateRequest request) {
        UUID merchantId = SecurityUtils.getCurrentMerchantId();

        Group group = Group.builder()
            .merchantId(merchantId)
            .name(request.name())
            .description(request.description())
            .isSystem(false)
            .isActive(true)
            .build();

        Group created = groupService.createWithRules(group, request.ruleIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(groupMapper.toDetailResponse(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar grupo", description = "Atualiza os dados de um grupo")
    public ResponseEntity<GroupDetailResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody GroupUpdateRequest request
    ) {
        Group groupData = Group.builder()
            .name(request.name())
            .description(request.description())
            .isActive(request.isActive())
            .build();

        Group updated = groupService.updateWithRules(id, groupData, request.ruleIds());
        return ResponseEntity.ok(groupMapper.toDetailResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir grupo", description = "Desativa um grupo")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
