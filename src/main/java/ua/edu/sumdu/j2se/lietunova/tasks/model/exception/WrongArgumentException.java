package ua.edu.sumdu.j2se.lietunova.tasks.model.exception;

public class WrongArgumentException extends Exception {
    public WrongArgumentException() {
    }

    public WrongArgumentException(String message) {
        super(message);
    }

    public WrongArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongArgumentException(Throwable cause) {
        super(cause);
    }

    public WrongArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
