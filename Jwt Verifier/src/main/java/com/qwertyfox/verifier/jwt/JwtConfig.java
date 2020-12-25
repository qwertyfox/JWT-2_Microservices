package com.qwertyfox.verifier.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
@Data
public class JwtConfig {

    public String secretKey;
    public String tokenPrefix;
}
