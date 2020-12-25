package com.qwertyfox.webserver.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "application.jwt")
@NoArgsConstructor
@Data
public class MyJwtConfig {

    public String secretKey;
    public String tokenPrefix;
    public int tokenExpiryAfterDays;

}
