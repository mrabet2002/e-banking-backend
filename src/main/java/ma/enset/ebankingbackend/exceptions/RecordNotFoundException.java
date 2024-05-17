package ma.enset.ebankingbackend.exceptions;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends GlobalApiException {
    public RecordNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

//    Default message
    public RecordNotFoundException() {
        super("Record not found!", HttpStatus.NOT_FOUND);
    }
}
