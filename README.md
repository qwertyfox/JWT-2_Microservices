# JWT-2_Microservices
Microservice Architecture with JWT exchange as security

## Ports
- Eureka server: localhost:8761
- Web service: localhost:9090
- Verifier service: localhost:9091


## Overall
- This project has 3 services (Eureka server, Verifier server and Web server). <br>
- Web server sends a JWT signed with secret key to Verifier service.<br>
- Verifier service then decodes the JWT and appropriately displays wheather the token is valid or invalid in the console.<br>
- If JWT is tampered with, the Verifier service will not validate the token.<br>

## Codes
- Uses Nimbus library to parse the token.<br>
- application.properties has defined values which is used by JwtConfig.java file using @ConfigurationProperties(prefix = //prefix).<br>
- JWT is created by Web service and signed. <br>
- Http header with name Authorization is created using HttpHeader.AUTHORIZATION and token is attached. <br>
- HttpEntity is created to attach the new header. <br>
- RestTemplate is used to post JWT to Verifier service URL (localhost:9091/verifyToken) which then verifies. <br>

## Testing
- Using Postman, token can be extracted using localhost:9091/test. <br>
- Token can then be altered or decoded using [jwt.io](http://jwt.io). <br>
- New request to the Verifier service then can be sent separately in localhost:9091/verifyToken with POST request and<br>
token under request heading Authorization.
