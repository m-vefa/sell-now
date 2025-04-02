package zsell.com.listingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ZsellException extends RuntimeException {

    private final String key;
    private final String[] args;
    private final HttpStatus httpStatus;

    public ZsellException(String key, HttpStatus httpStatus, String... args) {
        this.key = key;
        this.httpStatus = httpStatus;
        this.args = args;
    }

}
