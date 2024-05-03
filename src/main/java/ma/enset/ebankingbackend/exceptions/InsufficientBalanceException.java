package ma.enset.ebankingbackend.exceptions;

public class InsufficientBalanceException extends BadeRequestException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
