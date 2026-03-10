package com.minhaempresa.meusaas.web.mapper;

import com.minhaempresa.meusaas.core.entity.Group;
import com.minhaempresa.meusaas.web.dto.response.GroupDetailResponse;
import com.minhaempresa.meusaas.web.dto.response.GroupListResponse;
import com.minhaempresa.meusaas.web.dto.response.GroupSimpleResponse;
import com.minhaempresa.meusaas.web.dto.response.RuleResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-10T16:58:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public GroupListResponse toListResponse(Group group) {
        if ( group == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String description = null;
        boolean isSystem = false;
        boolean isActive = false;
        LocalDateTime createdAt = null;

        id = group.getId();
        name = group.getName();
        description = group.getDescription();
        if ( group.getIsSystem() != null ) {
            isSystem = group.getIsSystem();
        }
        if ( group.getIsActive() != null ) {
            isActive = group.getIsActive();
        }
        createdAt = group.getCreatedAt();

        int userCount = group.getUsers() != null ? group.getUsers().size() : 0;

        GroupListResponse groupListResponse = new GroupListResponse( id, name, description, isSystem, isActive, userCount, createdAt );

        return groupListResponse;
    }

    @Override
    public List<GroupListResponse> toListResponse(List<Group> groups) {
        if ( groups == null ) {
            return null;
        }

        List<GroupListResponse> list = new ArrayList<GroupListResponse>( groups.size() );
        for ( Group group : groups ) {
            list.add( toListResponse( group ) );
        }

        return list;
    }

    @Override
    public GroupDetailResponse toDetailResponse(Group group) {
        if ( group == null ) {
            return null;
        }

        List<RuleResponse> rules = null;
        UUID id = null;
        String name = null;
        String description = null;
        boolean isSystem = false;
        boolean isActive = false;
        LocalDateTime createdAt = null;

        rules = mapRules( group.getRules() );
        id = group.getId();
        name = group.getName();
        description = group.getDescription();
        if ( group.getIsSystem() != null ) {
            isSystem = group.getIsSystem();
        }
        if ( group.getIsActive() != null ) {
            isActive = group.getIsActive();
        }
        createdAt = group.getCreatedAt();

        int userCount = group.getUsers() != null ? group.getUsers().size() : 0;

        GroupDetailResponse groupDetailResponse = new GroupDetailResponse( id, name, description, isSystem, isActive, userCount, createdAt, rules );

        return groupDetailResponse;
    }

    @Override
    public GroupSimpleResponse toSimpleResponse(Group group) {
        if ( group == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = group.getId();
        name = group.getName();

        GroupSimpleResponse groupSimpleResponse = new GroupSimpleResponse( id, name );

        return groupSimpleResponse;
    }

    @Override
    public List<GroupSimpleResponse> toSimpleResponse(List<Group> groups) {
        if ( groups == null ) {
            return null;
        }

        List<GroupSimpleResponse> list = new ArrayList<GroupSimpleResponse>( groups.size() );
        for ( Group group : groups ) {
            list.add( toSimpleResponse( group ) );
        }

        return list;
    }
}
