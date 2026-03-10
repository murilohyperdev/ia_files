package com.minhaempresa.meusaas.web.mapper;

import com.minhaempresa.meusaas.core.entity.Group;
import com.minhaempresa.meusaas.core.entity.Rule;
import com.minhaempresa.meusaas.web.dto.response.GroupDetailResponse;
import com.minhaempresa.meusaas.web.dto.response.GroupListResponse;
import com.minhaempresa.meusaas.web.dto.response.GroupSimpleResponse;
import com.minhaempresa.meusaas.web.dto.response.RuleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "userCount", expression = "java(group.getUsers() != null ? group.getUsers().size() : 0)")
    GroupListResponse toListResponse(Group group);

    List<GroupListResponse> toListResponse(List<Group> groups);

    @Mapping(target = "userCount", expression = "java(group.getUsers() != null ? group.getUsers().size() : 0)")
    @Mapping(target = "rules", source = "rules", qualifiedByName = "mapRules")
    GroupDetailResponse toDetailResponse(Group group);

    GroupSimpleResponse toSimpleResponse(Group group);

    List<GroupSimpleResponse> toSimpleResponse(List<Group> groups);

    @Named("mapRules")
    default List<RuleResponse> mapRules(Set<Rule> rules) {
        if (rules == null) {
            return Collections.emptyList();
        }
        return rules.stream()
            .map(rule -> new RuleResponse(
                rule.getId(),
                rule.getCode(),
                rule.getName(),
                rule.getDescription(),
                rule.getModule()
            ))
            .collect(Collectors.toList());
    }
}
