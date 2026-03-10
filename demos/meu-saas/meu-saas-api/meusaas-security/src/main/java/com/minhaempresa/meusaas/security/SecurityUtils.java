package com.minhaempresa.meusaas.security;

import com.minhaempresa.meusaas.security.userdetails.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public final class SecurityUtils {

    private SecurityUtils() {
        // Utility class
    }

    public static Optional<CustomUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return Optional.of(userDetails);
        }
        return Optional.empty();
    }

    public static UUID getCurrentUserId() {
        return getCurrentUser()
            .map(CustomUserDetails::getId)
            .orElse(null);
    }

    public static UUID getCurrentMerchantId() {
        return getCurrentUser()
            .map(CustomUserDetails::getMerchantId)
            .orElse(null);
    }

    public static String getCurrentUserEmail() {
        return getCurrentUser()
            .map(CustomUserDetails::getEmail)
            .orElse(null);
    }

    public static boolean hasAuthority(String authority) {
        return getCurrentUser()
            .map(user -> user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(authority)))
            .orElse(false);
    }
}
