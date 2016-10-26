package viper.comms.dao.conn;

import viper.comms.dao.exception.RetryFailedException;

public interface Retryable {

    /**
    Attempts a retryable operation one time.

    @returns true if the operation is successful or
             false if the operation fails and needs
             to be retried.

    @throws  RetryFailedException if an unexpected error
             occurs and retrying the operation will not
             fix it.
    */
    boolean attempt() throws RetryFailedException;

    /**
    Recovers from an operation failure.
    */
    void recover() throws RetryFailedException;
}

