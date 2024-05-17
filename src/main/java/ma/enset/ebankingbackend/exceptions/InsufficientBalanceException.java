package ma.enset.ebankingbackend.exceptions;

public class InsufficientBalanceException extends BadRequestException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
