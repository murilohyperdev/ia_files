package com.minhaempresa.meusaas.web.mapper;

import com.minhaempresa.meusaas.core.entity.User;
import com.minhaempresa.meusaas.web.dto.response.GroupResponse;
import com.minhaempresa.meusaas.web.dto.response.GroupSimpleResponse;
import com.minhaempresa.meusaas.web.dto.response.UserListResponse;
import com.minhaempresa.meusaas.web.dto.response.UserResponse;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        List<GroupResponse> groups = null;
        List<String> rules = null;
        UUID id = null;
        UUID merchantId = null;
        String name = null;
        String email = null;
        String avatarUrl = null;
        String phone = null;
        boolean isActive = false;
        LocalDateTime emailVerifiedAt = null;
        LocalDateTime lastLoginAt = null;
        LocalDateTime createdAt = null;

        groups = mapGroups( user.getGroups() );
        rules = mapRules( user.getGroups() );
        id = user.getId();
        merchantId = user.getMerchantId();
        name = user.getName();
        email = user.getEmail();
        avatarUrl = user.getAvatarUrl();
        phone = user.getPhone();
        if ( user.getIsActive() != null ) {
            isActive = user.getIsActive();
        }
        emailVerifiedAt = user.getEmailVerifiedAt();
        lastLoginAt = user.getLastLoginAt();
        createdAt = user.getCreatedAt();

        UserResponse userResponse = new UserResponse( id, merchantId, name, email, avatarUrl, phone, isActive, emailVerifiedAt, lastLoginAt, createdAt, groups, rules );

        return userResponse;
    }

    @Override
    public UserListResponse toListResponse(User user) {
        if ( user == null ) {
            return null;
        }

        List<GroupSimpleResponse> groups = null;
        UUID id = null;
        String name = null;
        String email = null;
        String avatarUrl = null;
        String phone = null;
        boolean isActive = false;
        LocalDateTime lastLoginAt = null;
        LocalDateTime createdAt = null;

        groups = mapGroupsSimple( user.getGroups() );
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        avatarUrl = user.getAvatarUrl();
        phone = user.getPhone();
        if ( user.getIsActive() != null ) {
            isActive = user.getIsActive();
        }
        lastLoginAt = user.getLastLoginAt();
        createdAt = user.getCreatedAt();

        UserListResponse userListResponse = new UserListResponse( id, name, email, avatarUrl, phone, isActive, lastLoginAt, createdAt, groups );

        return userListResponse;
    }

    @Override
    public List<UserListResponse> toListResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserListResponse> list = new ArrayList<UserListResponse>( users.size() );
        for ( User user : users ) {
            list.add( toListResponse( user ) );
        }

        return list;
    }
}
