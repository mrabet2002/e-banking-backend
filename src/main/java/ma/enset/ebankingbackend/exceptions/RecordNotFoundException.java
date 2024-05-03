package ma.enset.ebankingbackend.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }

//    Default message
    public RecordNotFoundException() {
        super("Record not found!");
    }
}
