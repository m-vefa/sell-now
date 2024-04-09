package org.zsell.userservice.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ZsellException extends RuntimeException {

    private static final long serialVersionUID = 287436834L;
    private final String key;
    private final String[] args;
    private final HttpStatus httpStatus;

    public ZsellException(String key, HttpStatus httpStatus, String... args) {
        this.key = key;
        this.httpStatus = httpStatus;
        this.args = args;
    }

    public ZsellException(String key, HttpStatus httpStatus) {
        this.key = key;
        this.httpStatus = httpStatus;
        this.args = new String[0];
    }
}
