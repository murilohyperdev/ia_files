package com.minhaempresa.meusaas.core.repository;

import com.minhaempresa.meusaas.core.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

    @Query("SELECT g FROM Group g WHERE g.merchantId = :merchantId AND g.isActive = true")
    List<Group> findAllByMerchantId(@Param("merchantId") UUID merchantId);

    @Query("SELECT g FROM Group g WHERE g.merchantId = :merchantId AND g.isActive = true")
    Page<Group> findAllByMerchantId(@Param("merchantId") UUID merchantId, Pageable pageable);

    @Query("SELECT g FROM Group g LEFT JOIN FETCH g.rules WHERE g.id = :id")
    Optional<Group> findByIdWithRules(@Param("id") UUID id);

    @Query("SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.rules LEFT JOIN FETCH g.users WHERE g.id = :id AND g.merchantId = :merchantId")
    Optional<Group> findByIdWithRulesAndUsers(@Param("id") UUID id, @Param("merchantId") UUID merchantId);

    @Query("SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.users WHERE g.merchantId = :merchantId")
    List<Group> findAllByMerchantIdWithUsers(@Param("merchantId") UUID merchantId);

    @Query(value = "SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.users WHERE g.merchantId = :merchantId",
           countQuery = "SELECT COUNT(g) FROM Group g WHERE g.merchantId = :merchantId")
    Page<Group> findAllByMerchantIdWithUsersPaged(@Param("merchantId") UUID merchantId, Pageable pageable);

    List<Group> findAllByIdInAndMerchantId(List<UUID> ids, UUID merchantId);

    Optional<Group> findByNameAndMerchantId(String name, UUID merchantId);

    boolean existsByNameAndMerchantId(String name, UUID merchantId);
}
