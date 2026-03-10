package com.minhaempresa.meusaas.core.repository;

import com.minhaempresa.meusaas.core.entity.User;
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
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.merchantId = :merchantId AND u.deletedAt IS NULL")
    Optional<User> findByEmailAndMerchantId(@Param("email") String email, @Param("merchantId") UUID merchantId);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.deletedAt IS NULL")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.groups g LEFT JOIN FETCH g.rules WHERE u.id = :id AND u.deletedAt IS NULL")
    Optional<User> findByIdWithGroupsAndRules(@Param("id") UUID id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.groups g LEFT JOIN FETCH g.rules WHERE u.email = :email AND u.deletedAt IS NULL")
    Optional<User> findByEmailWithGroupsAndRules(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.merchantId = :merchantId AND u.deletedAt IS NULL")
    Page<User> findAllByMerchantId(@Param("merchantId") UUID merchantId, Pageable pageable);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.groups WHERE u.merchantId = :merchantId AND u.deletedAt IS NULL " +
           "AND (:search IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:isActive IS NULL OR u.isActive = :isActive)")
    List<User> findAllByMerchantIdWithFilters(
        @Param("merchantId") UUID merchantId,
        @Param("search") String search,
        @Param("isActive") Boolean isActive
    );

    @Query(value = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.groups WHERE u.merchantId = :merchantId AND u.deletedAt IS NULL " +
           "AND (:search IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:isActive IS NULL OR u.isActive = :isActive)",
           countQuery = "SELECT COUNT(DISTINCT u) FROM User u WHERE u.merchantId = :merchantId AND u.deletedAt IS NULL " +
           "AND (:search IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:isActive IS NULL OR u.isActive = :isActive)")
    Page<User> findAllByMerchantIdWithFiltersPaged(
        @Param("merchantId") UUID merchantId,
        @Param("search") String search,
        @Param("isActive") Boolean isActive,
        Pageable pageable
    );

    boolean existsByEmailAndMerchantId(String email, UUID merchantId);
}
