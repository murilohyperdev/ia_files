package com.minhaempresa.meusaas.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.jwt")
@Getter
@Setter
public class JwtConfig {

    private String secret = "meusaas-jwt-secret-key-that-should-be-changed-in-production-environment";
    private long accessTokenExpirationMinutes = 30;
    private long refreshTokenExpirationDays = 30;
}
