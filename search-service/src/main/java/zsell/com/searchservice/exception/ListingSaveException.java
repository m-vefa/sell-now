package zsell.com.searchservice.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class ListingSaveException extends ZsellException {
    public ListingSaveException(String... args) {
        super("ListingPublishException", HttpStatus.SERVICE_UNAVAILABLE, args);
    }
}
