package com.minhaempresa.meusaas.core.service;

import com.minhaempresa.meusaas.core.entity.Group;
import com.minhaempresa.meusaas.core.entity.Rule;
import com.minhaempresa.meusaas.core.exception.BusinessException;
import com.minhaempresa.meusaas.core.exception.DuplicateResourceException;
import com.minhaempresa.meusaas.core.exception.ResourceNotFoundException;
import com.minhaempresa.meusaas.core.repository.GroupRepository;
import com.minhaempresa.meusaas.core.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final RuleRepository ruleRepository;

    @Transactional(readOnly = true)
    public Group findById(UUID id) {
        return groupRepository.findByIdWithRules(id)
            .orElseThrow(() -> new ResourceNotFoundException("Grupo", "id", id));
    }

    @Transactional(readOnly = true)
    public List<Group> findAllByMerchantId(UUID merchantId) {
        return groupRepository.findAllByMerchantId(merchantId);
    }

    @Transactional(readOnly = true)
    public Page<Group> findAllByMerchantId(UUID merchantId, Pageable pageable) {
        return groupRepository.findAllByMerchantId(merchantId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Group> findAllByMerchantIdWithUsers(UUID merchantId, Pageable pageable) {
        return groupRepository.findAllByMerchantIdWithUsersPaged(merchantId, pageable);
    }

    @Transactional(readOnly = true)
    public Group findByIdWithRulesAndUsers(UUID id, UUID merchantId) {
        return groupRepository.findByIdWithRulesAndUsers(id, merchantId)
            .orElseThrow(() -> new ResourceNotFoundException("Grupo", "id", id));
    }

    @Transactional(readOnly = true)
    public List<Group> findAllSimple(UUID merchantId) {
        return groupRepository.findAllByMerchantId(merchantId);
    }

    @Transactional
    public Group create(Group group) {
        if (groupRepository.existsByNameAndMerchantId(group.getName(), group.getMerchantId())) {
            throw new DuplicateResourceException("Grupo", "nome", group.getName());
        }
        return groupRepository.save(group);
    }

    @Transactional
    public Group update(UUID id, Group groupData) {
        Group group = findById(id);

        if (group.getIsSystem()) {
            throw new BusinessException("Grupos do sistema não podem ser editados");
        }

        if (!group.getName().equals(groupData.getName()) &&
            groupRepository.existsByNameAndMerchantId(groupData.getName(), group.getMerchantId())) {
            throw new DuplicateResourceException("Grupo", "nome", groupData.getName());
        }

        group.setName(groupData.getName());
        group.setDescription(groupData.getDescription());
        group.setRules(groupData.getRules());

        return groupRepository.save(group);
    }

    @Transactional
    public void delete(UUID id) {
        Group group = findById(id);

        if (group.getIsSystem()) {
            throw new BusinessException("Grupos do sistema não podem ser removidos");
        }

        group.setIsActive(false);
        groupRepository.save(group);
    }

    @Transactional
    public Group createWithRules(Group group, List<UUID> ruleIds) {
        if (groupRepository.existsByNameAndMerchantId(group.getName(), group.getMerchantId())) {
            throw new DuplicateResourceException("Grupo", "nome", group.getName());
        }

        if (ruleIds != null && !ruleIds.isEmpty()) {
            Set<Rule> rules = new HashSet<>(ruleRepository.findAllByIdIn(ruleIds));
            group.setRules(rules);
        }

        return groupRepository.save(group);
    }

    @Transactional
    public Group updateWithRules(UUID id, Group groupData, List<UUID> ruleIds) {
        Group group = findById(id);

        if (group.getIsSystem()) {
            throw new BusinessException("Grupos do sistema não podem ser editados");
        }

        if (!group.getName().equals(groupData.getName()) &&
            groupRepository.existsByNameAndMerchantId(groupData.getName(), group.getMerchantId())) {
            throw new DuplicateResourceException("Grupo", "nome", groupData.getName());
        }

        group.setName(groupData.getName());
        group.setDescription(groupData.getDescription());
        group.setIsActive(groupData.getIsActive());

        if (ruleIds != null) {
            Set<Rule> rules = new HashSet<>(ruleRepository.findAllByIdIn(ruleIds));
            group.setRules(rules);
        }

        return groupRepository.save(group);
    }
}
