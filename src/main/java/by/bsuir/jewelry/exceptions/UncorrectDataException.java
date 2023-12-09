package by.bsuir.jewelry.exceptions;

public class UncorrectDataException extends Exception {
    public UncorrectDataException() {
        super();
    }

    public UncorrectDataException(String message) {
        super(message);
    }

    public UncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncorrectDataException(Throwable cause) {
        super(cause);
    }
}
