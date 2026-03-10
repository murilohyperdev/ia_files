package com.minhaempresa.meusaas.core.repository;

import com.minhaempresa.meusaas.core.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    Optional<Merchant> findBySlug(String slug);

    Optional<Merchant> findByEmail(String email);

    boolean existsBySlug(String slug);

    boolean existsByEmail(String email);
}
