package com.minhaempresa.meusaas.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rule extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String module;

    @ManyToMany(mappedBy = "rules")
    @Builder.Default
    private Set<Group> groups = new HashSet<>();
}
