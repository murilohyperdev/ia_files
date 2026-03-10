package com.minhaempresa.meusaas.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "t_group", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"merchant_id", "name"})
})
@Filter(name = "tenantFilter", condition = "merchant_id = :merchantId")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group extends BaseEntity {

    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "is_system")
    @Builder.Default
    private Boolean isSystem = false;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "t_group_rule",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    @Builder.Default
    private Set<Rule> rules = new HashSet<>();

    @ManyToMany(mappedBy = "groups")
    @Builder.Default
    private Set<User> users = new HashSet<>();
}
