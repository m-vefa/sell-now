package org.zsell.agentgateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zsell.agentgateway.exception.AuthenticationFailedException;
import org.zsell.agentgateway.model.response.auth.UserProfile;
import org.zsell.agentgateway.service.auth.UserAuthentication;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAuthUtils {

    public static UserProfile getUser() {
        final UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(userAuthentication)) {
            throw new AuthenticationFailedException("Authentication required. Please log in.");
        }
        return userAuthentication.getDetails();
    }
}