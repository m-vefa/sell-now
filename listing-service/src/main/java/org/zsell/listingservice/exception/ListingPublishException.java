package org.zsell.listingservice.exception;

import org.springframework.http.HttpStatus;

public class ListingPublishException extends ZsellException {
    public ListingPublishException(String... args) {
        super("ListingPublishException", HttpStatus.NOT_FOUND, args);
    }

}
