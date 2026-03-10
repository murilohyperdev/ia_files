package com.minhaempresa.meusaas.web.mapper;

import com.minhaempresa.meusaas.core.entity.Group;
import com.minhaempresa.meusaas.core.entity.User;
import com.minhaempresa.meusaas.web.dto.response.GroupResponse;
import com.minhaempresa.meusaas.web.dto.response.GroupSimpleResponse;
import com.minhaempresa.meusaas.web.dto.response.UserListResponse;
import com.minhaempresa.meusaas.web.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "groups", source = "groups", qualifiedByName = "mapGroups")
    @Mapping(target = "rules", source = "groups", qualifiedByName = "mapRules")
    UserResponse toResponse(User user);

    @Mapping(target = "groups", source = "groups", qualifiedByName = "mapGroupsSimple")
    UserListResponse toListResponse(User user);

    List<UserListResponse> toListResponse(List<User> users);

    @Named("mapGroups")
    default List<GroupResponse> mapGroups(Set<Group> groups) {
        if (groups == null) {
            return Collections.emptyList();
        }
        return groups.stream()
            .map(group -> new GroupResponse(group.getId(), group.getName(), group.getDescription()))
            .collect(Collectors.toList());
    }

    @Named("mapGroupsSimple")
    default List<GroupSimpleResponse> mapGroupsSimple(Set<Group> groups) {
        if (groups == null) {
            return Collections.emptyList();
        }
        return groups.stream()
            .map(group -> new GroupSimpleResponse(group.getId(), group.getName()))
            .collect(Collectors.toList());
    }

    @Named("mapRules")
    default List<String> mapRules(Set<Group> groups) {
        if (groups == null) {
            return Collections.emptyList();
        }
        return groups.stream()
            .flatMap(group -> group.getRules().stream())
            .map(rule -> rule.getCode())
            .distinct()
            .collect(Collectors.toList());
    }
}
