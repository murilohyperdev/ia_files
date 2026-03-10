package com.minhaempresa.meusaas.core.service;

import com.minhaempresa.meusaas.core.entity.Merchant;
import com.minhaempresa.meusaas.core.exception.DuplicateResourceException;
import com.minhaempresa.meusaas.core.exception.ResourceNotFoundException;
import com.minhaempresa.meusaas.core.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    @Transactional(readOnly = true)
    public Merchant findById(UUID id) {
        return merchantRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Merchant", "id", id));
    }

    @Transactional(readOnly = true)
    public Merchant findBySlug(String slug) {
        return merchantRepository.findBySlug(slug)
            .orElseThrow(() -> new ResourceNotFoundException("Merchant", "slug", slug));
    }

    @Transactional
    public Merchant create(Merchant merchant) {
        if (merchantRepository.existsBySlug(merchant.getSlug())) {
            throw new DuplicateResourceException("Merchant", "slug", merchant.getSlug());
        }
        if (merchantRepository.existsByEmail(merchant.getEmail())) {
            throw new DuplicateResourceException("Merchant", "email", merchant.getEmail());
        }
        return merchantRepository.save(merchant);
    }

    @Transactional
    public Merchant update(UUID id, Merchant merchantData) {
        Merchant merchant = findById(id);

        if (!merchant.getSlug().equals(merchantData.getSlug()) &&
            merchantRepository.existsBySlug(merchantData.getSlug())) {
            throw new DuplicateResourceException("Merchant", "slug", merchantData.getSlug());
        }

        merchant.setName(merchantData.getName());
        merchant.setSlug(merchantData.getSlug());
        merchant.setEmail(merchantData.getEmail());
        merchant.setPhone(merchantData.getPhone());
        merchant.setDocument(merchantData.getDocument());
        merchant.setLogoUrl(merchantData.getLogoUrl());

        return merchantRepository.save(merchant);
    }

    public String generateSlug(String name) {
        String baseSlug = name.toLowerCase()
            .replaceAll("[^a-z0-9\\s-]", "")
            .replaceAll("\\s+", "-")
            .replaceAll("-+", "-")
            .replaceAll("^-|-$", "");

        String slug = baseSlug;
        int counter = 1;

        while (merchantRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + counter;
            counter++;
        }

        return slug;
    }
}
