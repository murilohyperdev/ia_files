package com.minhaempresa.meusaas.core.service;

import com.minhaempresa.meusaas.core.entity.Group;
import com.minhaempresa.meusaas.core.entity.User;
import com.minhaempresa.meusaas.core.exception.DuplicateResourceException;
import com.minhaempresa.meusaas.core.exception.ResourceNotFoundException;
import com.minhaempresa.meusaas.core.repository.GroupRepository;
import com.minhaempresa.meusaas.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Transactional(readOnly = true)
    public User findById(UUID id) {
        return userRepository.findByIdWithGroupsAndRules(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", id));
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmailWithGroupsAndRules(email)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário", "email", email));
    }

    @Transactional(readOnly = true)
    public Page<User> findAllByMerchantId(UUID merchantId, Pageable pageable) {
        return userRepository.findAllByMerchantId(merchantId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<User> findAllByMerchantIdWithFilters(UUID merchantId, String search, Boolean isActive, Pageable pageable) {
        return userRepository.findAllByMerchantIdWithFiltersPaged(merchantId, search, isActive, pageable);
    }

    @Transactional
    public User create(User user) {
        if (userRepository.existsByEmailAndMerchantId(user.getEmail(), user.getMerchantId())) {
            throw new DuplicateResourceException("Usuário", "email", user.getEmail());
        }
        return userRepository.save(user);
    }

    @Transactional
    public User update(UUID id, User userData) {
        User user = findById(id);

        if (!user.getEmail().equals(userData.getEmail()) &&
            userRepository.existsByEmailAndMerchantId(userData.getEmail(), user.getMerchantId())) {
            throw new DuplicateResourceException("Usuário", "email", userData.getEmail());
        }

        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPhone(userData.getPhone());
        user.setAvatarUrl(userData.getAvatarUrl());

        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(UUID id, String passwordHash) {
        User user = findById(id);
        user.setPasswordHash(passwordHash);
        userRepository.save(user);
    }

    @Transactional
    public void updateLastLogin(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", id));
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Transactional
    public void softDelete(UUID id) {
        User user = findById(id);
        user.setDeletedAt(LocalDateTime.now());
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public User updateWithGroups(UUID id, User userData, List<UUID> groupIds, UUID merchantId) {
        User user = findById(id);

        if (!user.getEmail().equals(userData.getEmail()) &&
            userRepository.existsByEmailAndMerchantId(userData.getEmail(), user.getMerchantId())) {
            throw new DuplicateResourceException("Usuário", "email", userData.getEmail());
        }

        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPhone(userData.getPhone());
        user.setIsActive(userData.getIsActive());

        if (groupIds != null) {
            Set<Group> groups = new HashSet<>(groupRepository.findAllByIdInAndMerchantId(groupIds, merchantId));
            user.setGroups(groups);
        }

        return userRepository.save(user);
    }

    @Transactional
    public User createWithGroups(User user, List<UUID> groupIds, UUID merchantId) {
        if (userRepository.existsByEmailAndMerchantId(user.getEmail(), user.getMerchantId())) {
            throw new DuplicateResourceException("Usuário", "email", user.getEmail());
        }

        if (groupIds != null && !groupIds.isEmpty()) {
            Set<Group> groups = new HashSet<>(groupRepository.findAllByIdInAndMerchantId(groupIds, merchantId));
            user.setGroups(groups);
        }

        return userRepository.save(user);
    }

    @Transactional
    public void updateGroups(UUID userId, List<UUID> groupIds, UUID merchantId) {
        User user = findById(userId);
        Set<Group> groups = new HashSet<>(groupRepository.findAllByIdInAndMerchantId(groupIds, merchantId));
        user.setGroups(groups);
        userRepository.save(user);
    }
}
