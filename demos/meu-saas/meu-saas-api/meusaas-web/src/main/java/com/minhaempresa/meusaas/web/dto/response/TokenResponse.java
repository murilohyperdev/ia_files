package com.minhaempresa.meusaas.web.dto.response;

public record TokenResponse(
    String accessToken,
    String refreshToken,
    long expiresIn,
    UserResponse user
) {}
