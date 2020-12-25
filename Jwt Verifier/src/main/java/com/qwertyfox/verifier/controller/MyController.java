package com.qwertyfox.verifier.controller;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.qwertyfox.verifier.jwt.JwtConfig;
import com.qwertyfox.verifier.jwt.JwtSecretKey;
import io.jsonwebtoken.JwtException;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
public class MyController {

    private final JwtConfig jwtConfig;
    private final JwtSecretKey jwtSecretKey;

    @Autowired
    public MyController(JwtConfig jwtConfig, JwtSecretKey jwtSecretKey) {
        this.jwtConfig = jwtConfig;
        this.jwtSecretKey = jwtSecretKey;
    }

    private final String validToken = "Token is valid";
    private final String noToken = "Proper token is not present";
    private final String invalidToken = "Token is invalid";

    @PostMapping("/verifyToken")
    public void verify (HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorizationHeader == null || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
            System.out.println(noToken);
        }else{
            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(),"");
            System.out.println(token);

            try{
                JWSVerifier verifier = new MACVerifier(jwtSecretKey.getSecretKey());
                SignedJWT signedJWT = SignedJWT.parse(token);
                boolean flag = signedJWT.verify(verifier);

                if(flag) {
                    System.out.println(validToken);
                    // Some codes to perform logics here


                }else {
                    System.out.println(invalidToken);
                }
            }catch (JwtException | JOSEException | ParseException e){
                System.out.println(invalidToken);
            }
        }
    }


}
