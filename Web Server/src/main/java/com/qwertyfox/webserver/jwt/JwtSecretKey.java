package com.qwertyfox.webserver.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecretKey {

    private final MyJwtConfig myJwtConfig;

    @Autowired
    public JwtSecretKey(MyJwtConfig myJwtConfig) {
        this.myJwtConfig = myJwtConfig;
    }

    @Bean
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(myJwtConfig.secretKey.getBytes());
    }

}
