package ufrn.imd.FallBank.CustomFeatures.Exceptions;

public class BankException extends Exception{
    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable cause) {
        super(message, cause);
    }
}
