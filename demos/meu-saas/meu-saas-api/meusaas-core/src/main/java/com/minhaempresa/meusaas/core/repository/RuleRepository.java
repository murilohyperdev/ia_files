package com.minhaempresa.meusaas.core.repository;

import com.minhaempresa.meusaas.core.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RuleRepository extends JpaRepository<Rule, UUID> {

    Optional<Rule> findByCode(String code);

    List<Rule> findAllByModule(String module);

    boolean existsByCode(String code);

    @Query("SELECT DISTINCT r.module FROM Rule r ORDER BY r.module")
    List<String> findDistinctModules();

    List<Rule> findAllByIdIn(List<UUID> ids);
}
