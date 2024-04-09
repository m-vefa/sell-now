package org.zsell.agentgateway.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailedException extends AuthenticationException {

    public AuthenticationFailedException(String errorMessage) {
        super(errorMessage);
    }
}