package org.zsell.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ZsellException {

    public UserNotFoundException() {
        super("api.authentication.failed", HttpStatus.NOT_FOUND);
    }
}
