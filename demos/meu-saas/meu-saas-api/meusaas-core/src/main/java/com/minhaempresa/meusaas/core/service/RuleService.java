package com.minhaempresa.meusaas.core.service;

import com.minhaempresa.meusaas.core.entity.Rule;
import com.minhaempresa.meusaas.core.exception.ResourceNotFoundException;
import com.minhaempresa.meusaas.core.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RuleService {

    private final RuleRepository ruleRepository;

    @Transactional(readOnly = true)
    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Rule findById(UUID id) {
        return ruleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Permissão", "id", id));
    }

    @Transactional(readOnly = true)
    public Rule findByCode(String code) {
        return ruleRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Permissão", "código", code));
    }

    @Transactional(readOnly = true)
    public List<Rule> findByModule(String module) {
        return ruleRepository.findAllByModule(module);
    }

    @Transactional(readOnly = true)
    public List<String> findAllModules() {
        return ruleRepository.findDistinctModules();
    }

    @Transactional(readOnly = true)
    public Set<Rule> findByIds(List<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(ruleRepository.findAllByIdIn(ids));
    }
}
