package ma.enset.ebankingbackend.exceptions;

public class BadeRequestException extends RuntimeException {
    public BadeRequestException(String message) {
        super(message);
    }
}
