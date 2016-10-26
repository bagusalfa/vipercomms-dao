package viper.comms.dao.exception;
public class RetryFailedException extends Exception {

    public RetryFailedException(String message) {
        super(message);
    }

    public RetryFailedException(String message,
        Throwable cause) {
        super(message, cause);
    }
}

