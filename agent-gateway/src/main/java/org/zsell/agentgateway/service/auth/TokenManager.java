package org.zsell.agentgateway.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.zsell.agentgateway.client.UserServiceClient;
import org.zsell.agentgateway.exception.InvalidTokenException;
import org.zsell.agentgateway.exception.TokenGenerationException;
import org.zsell.agentgateway.model.auth.AuthenticationRequest;
import org.zsell.agentgateway.model.auth.AuthenticationResponse;
import org.zsell.agentgateway.model.auth.UserProfile;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.bouncycastle.util.encoders.Base64.decode;

@Service
@Slf4j
@RequiredArgsConstructor

public class TokenManager {
    private static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCKNVtPUGPzsF0S" +
            "toh+NWs+R9RkwOU3Fv1LCfuVcsFrFgiOWW60OoIwKIUhgMnnM7HOVHeXGvYNjFXv" +
            "YrwtJnjCLt0Cvaag762eoaAht7dJzT19QN7Wg/xuiq4iPnDdPr3WEiNJ01uCAJxo" +
            "4K1zKf4Tp0+hZMXPJnhrKqV34K2FErJUBpmQ1+9XKIhXKeNCAjQAnp4swjWFJN8u" +
            "dXdisKOXZ53BJvRKwTmf6JB/z2C6QcK36cQ7E16PL2jmOVuta6XrqT0g6rqB6VXp" +
            "qE6daDbU+jL+mnsuBPc4WR0Ny9A6aeRMVZSV411yajHaI7U5Gb7JNM9adP5RBN2r" +
            "43IOYt2TAgMBAAECggEAEJ0VWtVpDnQW3BcBFVrKh3QO/gWKCDNFQ23xxidDROIj" +
            "KSlq+1NFLxDP4BUhMB+wsgLJVWqyRTKnad1OjYbgYUdM3fV6QEJPdpArNaJl8AMx" +
            "7wqLPvIiuA8Hm6ox2wadv0vy3f8AMwJrytenQf68QhSMp/P7K0mOlCNOPLjPYv2N" +
            "JCJpKjFED2WTufwZF8cX4CgMeZL/d/Sni5s5zUeC7l+b8hqfNNHlwbkPOv//J1YR" +
            "Tnt5tq0l4Ioui2cOt9Kmd5WjIz0ivZJOiv7K8y84/ZEQoyk27ibYtYiA8sywMgFB" +
            "2cxVAd77JWD6hsq3QpN1USwxDuaIXizecUtU/AHlAQKBgQC+yD4ZWBxq/B9dZ37K" +
            "mnyqCUMpolGqShJcmcDxts5ZreXxqjHPff1il2TRxpUzrXEPAen8Fr1nyNbF7maX" +
            "M5cls0tXldwiPRQr3xDSi8doham30+98YzkGYHp6/QEXGwFOWOj4O/DlZuaHgclL" +
            "j8gGy/zc0ahMzR6Gt258kLDCUwKBgQC5dEVfHxyrp1jgFKdr+h6KDjyQXoXTutdB" +
            "IFiUNohE6Vvm6nw3uvyO+G9v0oiuJOMjek/YMJdTuOQjJOAFkYHjQk6Zq4su9dWd" +
            "jaKqNpo8cbFgUWhPuglDsc95eAJ0A714BUp22EpNyf/NMBVTU9UiW+0zOuN2ziT4" +
            "lR9KppyPwQKBgAHbdS9N+kjYV33TShqy0k/dczfuipj7y533KTexLeUVmk11+LIq" +
            "adMR4jtgkYdFQvqAbES1+/YMsHqQzZLfP7NwQWWC4fe7XHzo2QMTtUdBNdvCRmyR" +
            "kraLLeYQnHgbHTIkD3CLNNcm30umac1AgxP93GJR8C9/N7LcCklQJ0hdAoGADCxz" +
            "uVDDgwkTLnJsowviIVHZ6+gohMOJCb9j0SbbPxrKa5gM22Os3H22YvLBAh1tw5Bt" +
            "2VUnOdmzMBNlmTf5/L8HhdLa6hQ9F4CKov2+liUobZgqbsFZhlYPtnuIPbFZKJ3A" +
            "S5jbF8wgvIlhfPzNytmZ8nj1IYFar+6qBS52rIECgYBxC80Q+VbGf/k6dI2qc6oP" +
            "2oWnZO1RIQXciWW2BteNKvyvWDNeYGqIYmXzZ8xjfF8dvLgWrFYOo2WYGzHGJ/pj" +
            "IHegir/l0Nb2YfpVC6oLq42KAx59BP+ghe/WfRHa2hObjU0MUqsiPAAI+o+LnRjR" +
            "JRWtm1ZSKO/8qz96Psax4g==";

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAijVbT1Bj87BdEraIfjVr" +
            "PkfUZMDlNxb9Swn7lXLBaxYIjllutDqCMCiFIYDJ5zOxzlR3lxr2DYxV72K8LSZ4" +
            "wi7dAr2moO+tnqGgIbe3Sc09fUDe1oP8boquIj5w3T691hIjSdNbggCcaOCtcyn+" +
            "E6dPoWTFzyZ4ayqld+CthRKyVAaZkNfvVyiIVynjQgI0AJ6eLMI1hSTfLnV3YrCj" +
            "l2edwSb0SsE5n+iQf89gukHCt+nEOxNejy9o5jlbrWul66k9IOq6gelV6ahOnWg2" +
            "1Poy/pp7LgT3OFkdDcvQOmnkTFWUleNdcmox2iO1ORm+yTTPWnT+UQTdq+NyDmLd" +
            "kwIDAQAB";
    private final UserServiceClient userServiceClient;

    public Optional<Claims> decodeToken(String jwt) {
        try {
            return Optional.ofNullable(Jwts.parser()
                    .verifyWith(getPublicKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload());
        } catch (ExpiredJwtException ex) {
            log.error("Token expired", ex);
        } catch (Exception e) {
            log.error("Exception occurred while reading token", e);
        }
        return Optional.empty();
    }

    public String generateToken(UserProfile userProfile) {
        try {
            JwtBuilder jwtBuilder = createJwtBuilder(userProfile);
            return jwtBuilder.compact();
        } catch (Exception e) {
            throw new TokenGenerationException("Failed to generate token", e);
        }
    }

    private JwtBuilder createJwtBuilder(UserProfile userProfile) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .claim("id", userProfile.getId())
                .claim("firstName", userProfile.getFirstName())
                .claim("lastName", userProfile.getLastName())
                .claim("userName", userProfile.getUserName())
                .claim("email", userProfile.getEmail())
                .claim("phone", userProfile.getPhone())
                .claim("isActive", userProfile.getIsActive())
                .expiration(Date.from(Instant.now().plus(Duration.ofDays(30))))
                .issuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey());
    }

    private PublicKey getPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException {

        byte[] byteKey = decode(PUBLIC_KEY.getBytes(Charset.defaultCharset()));
        X509EncodedKeySpec publicKey = new X509EncodedKeySpec(byteKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(publicKey);

    }

    private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(PRIVATE_KEY));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    public Optional<Claims> getTokenClaims(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = getTokenFromHeader(authorizationHeader);
        if (token != null) {
            return decodeToken(token);
        }
        return Optional.empty();
    }

    private String getTokenFromHeader(String authorizationHeader) {

        if (authorizationHeader == null) {
            return null;
        }
        String[] parts = authorizationHeader.split(" ", 2);
        if (parts.length != 2 || !"Bearer".equalsIgnoreCase(parts[0])) {
            throw new InvalidTokenException("Invalid Authorization format");
        }

        return parts[1];
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        UserProfile userProfile = userServiceClient.login(authenticationRequest);
        String token = generateToken(userProfile);
        return AuthenticationResponse.builder()
                .jwtToken(token)
                .build();
    }
}
