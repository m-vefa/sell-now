package org.zsell.agentgateway.service.auth;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.zsell.agentgateway.model.auth.UserProfileConverter;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenManager tokenManager;
    private final UserProfileConverter userProfileConverter;

    public Authentication getUserAuthentication(HttpServletRequest httpServletRequest) {
        Optional<Claims> claims = tokenManager.getTokenClaims(httpServletRequest);
        return claims.map(value -> new UserAuthentication(userProfileConverter.apply(value))).orElse(null);
    }
}