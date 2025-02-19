package org.zsell.userservice.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends ZsellException {

    public AuthenticationFailedException() {
        super("api.authentication.failed", HttpStatus.NOT_ACCEPTABLE);
    }
}
