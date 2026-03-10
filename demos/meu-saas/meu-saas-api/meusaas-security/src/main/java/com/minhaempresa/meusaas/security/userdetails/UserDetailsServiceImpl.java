package com.minhaempresa.meusaas.security.userdetails;

import com.minhaempresa.meusaas.core.entity.User;
import com.minhaempresa.meusaas.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailWithGroupsAndRules(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        List<SimpleGrantedAuthority> authorities = user.getGroups().stream()
            .flatMap(group -> group.getRules().stream())
            .map(rule -> new SimpleGrantedAuthority(rule.getCode()))
            .collect(Collectors.toList());

        return new CustomUserDetails(
            user.getId(),
            user.getMerchantId(),
            user.getEmail(),
            user.getPasswordHash(),
            user.getName(),
            user.getIsActive(),
            authorities
        );
    }
}
