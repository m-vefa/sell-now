package org.zsell.listingservice.exception;

import org.springframework.http.HttpStatus;

public class ListingAlreadyPublishedException extends ZsellException {
    public ListingAlreadyPublishedException(String... args) {
        super("ListingAlreadyPublishedException", HttpStatus.IM_USED, args);
    }

}
