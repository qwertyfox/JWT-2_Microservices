package com.qwertyfox.webserver.controller;

import com.qwertyfox.webserver.jwt.MyJwtBuilder;
import com.qwertyfox.webserver.jwt.MyJwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyRestController {

    private final MyJwtBuilder myJwtBuilder;
    private final RestTemplate restTemplate;
    private final MyJwtConfig myJwtConfig;

    String url = "http://localhost:9091/verifyToken";
    // URL for Token Verifier server

    @Autowired
    public MyRestController(MyJwtBuilder myJwtBuilder, RestTemplate restTemplate, MyJwtConfig myJwtConfig) {
        this.myJwtBuilder = myJwtBuilder;
        this.restTemplate = restTemplate;
        this.myJwtConfig = myJwtConfig;
    }

    @GetMapping("/submitClicked")
    public void send() {

        String token = myJwtBuilder.buildSecuredToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,
                myJwtConfig.getTokenPrefix()+token);
        HttpEntity<?> request = new HttpEntity<>(headers);
        restTemplate.postForObject(url, request, Object.class);
    }


    // Test to alter the Token
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        String token = myJwtBuilder.buildSecuredToken();
        return "Bearer " +token;
    }



}
