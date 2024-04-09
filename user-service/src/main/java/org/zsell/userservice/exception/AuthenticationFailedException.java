package org.zsell.userservice.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends ZsellException {
    private static final long serialVersionUID = 3586723L;

    public AuthenticationFailedException() {
        super("api.authentication.failed", HttpStatus.NOT_ACCEPTABLE);
    }
}
