package zsell.com.listingservice.exception;

import org.springframework.http.HttpStatus;

public class ListingNotFoundException extends ZsellException {
    public ListingNotFoundException(String... args) {
        super("ListingNotFoundException", HttpStatus.NOT_FOUND, args);
    }

}