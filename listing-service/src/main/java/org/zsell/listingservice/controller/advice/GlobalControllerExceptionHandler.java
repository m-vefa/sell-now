package org.zsell.listingservice.controller.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.zsell.listingservice.exception.ListingAlreadyPublishedException;
import org.zsell.listingservice.exception.ListingNotFoundException;
import org.zsell.listingservice.exception.ListingPublishException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("Request method '" + exception.getMethod() + "' is not supported");
    }
    @ExceptionHandler(ListingAlreadyPublishedException.class)
    public ResponseEntity<Object> handleListingAlreadyPublishedException(ListingAlreadyPublishedException  exception, WebRequest request) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status",  exception.getHttpStatus().value());
        errorBody.put("key",  exception.getKey());
        errorBody.put("details", new String[]{"The listing with ID '" +  exception.getArgs()[0] + "' has already been published."});
        errorBody.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(errorBody,  exception.getHttpStatus());
    }

    @ExceptionHandler(ListingNotFoundException.class)
    public ResponseEntity<Object> handleListingNotFoundException(ListingNotFoundException exception, WebRequest request) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status",  exception.getHttpStatus().value());
        errorBody.put("key",  exception.getKey());
        errorBody.put("details", new String[]{"The listing with ID '" +  exception.getArgs()[0] + "' has not found."});
        errorBody.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorBody, exception.getHttpStatus());
    }

    @ExceptionHandler(ListingPublishException.class)
    public ResponseEntity<Object> handleListingPublishException(ListingPublishException exception, WebRequest request) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status",  exception.getHttpStatus().value());
        errorBody.put("key",  exception.getKey());
        errorBody.put("details", new String[]{String.format("Listing %s has unexpected activation state: %s",exception.getArgs()[0],exception.getArgs()[1])});
        errorBody.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorBody, exception.getHttpStatus());
    }
}