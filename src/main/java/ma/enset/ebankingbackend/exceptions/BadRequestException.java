package ma.enset.ebankingbackend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BadRequestException extends GlobalApiException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
