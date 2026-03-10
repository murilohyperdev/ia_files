package com.minhaempresa.meusaas.core.service;

import com.minhaempresa.meusaas.core.entity.RefreshToken;
import com.minhaempresa.meusaas.core.exception.UnauthorizedException;
import com.minhaempresa.meusaas.core.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.jwt.refresh-token-expiration-days:30}")
    private int refreshTokenExpirationDays;

    @Transactional
    public RefreshToken createRefreshToken(UUID userId) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(refreshTokenExpirationDays);

        RefreshToken refreshToken = RefreshToken.builder()
            .userId(userId)
            .token(token)
            .expiresAt(expiresAt)
            .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    public RefreshToken validateRefreshToken(String token) {
        return refreshTokenRepository.findValidToken(token, LocalDateTime.now())
            .orElseThrow(() -> new UnauthorizedException("Token de refresh inválido ou expirado"));
    }

    @Transactional
    public void revokeRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
            .ifPresent(refreshToken -> {
                refreshToken.setRevokedAt(LocalDateTime.now());
                refreshTokenRepository.save(refreshToken);
            });
    }

    @Transactional
    public void revokeAllUserTokens(UUID userId) {
        refreshTokenRepository.revokeAllByUserId(userId, LocalDateTime.now());
    }

    @Transactional
    public void cleanupExpiredTokens() {
        refreshTokenRepository.deleteExpiredAndRevoked(LocalDateTime.now());
    }
}
