package com.qwertyfox.webserver.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


@Configuration
public class MyJwtBuilder {

    public final MyJwtConfig myJwtConfig;
    public final JwtSecretKey jwtSecretKey;

    @Autowired
    public MyJwtBuilder(MyJwtConfig myJwtConfig, JwtSecretKey jwtSecretKey) {
        this.myJwtConfig = myJwtConfig;
        this.jwtSecretKey = jwtSecretKey;
    }


    public String buildSecuredToken() {

        return Jwts.builder()
                .setIssuedAt(new java.util.Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now()
                        .plusDays(myJwtConfig.getTokenExpiryAfterDays())))

                .claim("A1B2C3","obj") // for test purpose to manipulate JWT and see if it is detected
                .signWith(jwtSecretKey.getSecretKey())
                .compact();
    }
}
