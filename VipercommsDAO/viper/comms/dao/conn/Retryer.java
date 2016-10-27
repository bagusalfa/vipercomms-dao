package viper.comms.dao.conn;

import viper.comms.dao.exception.RetryFailedException;

public class Retryer {

    private Retryable operation;

    public Retryer(Retryable operation) {
        this.operation = operation;
    }

    /**
    Retries the operation until it succeeds.

    @param maximumAttempts The maximum number of times
                           to attempt the operation or
                           -1 to retry the operation
                           indefinitely.

    @param attemptInterval The number of milliseconds to
                           wait in between attempts or
                           0 to indicate no waiting.
    */
    public void perform(int maximumAttempts,
                        int attemptInterval)
                        throws RetryFailedException {

        boolean succeeded = false;

        // Retry until you reach the maximum number
        // of attempts or the operation succeeds.
        // If maximumAttempts is less than zero, then
        // there is no limit on the number of attempts.
        for(int i = 1;
            (i <= maximumAttempts
                || maximumAttempts < 0)
                && !succeeded;
            ++i) {

            // Attempt the operation.  If it was successful,
            // then set the succeeded flag so that the loop
            // ends.
            if(operation.attempt()) {
                succeeded = true;
            }

            // Otherwise recover, sleep, and loop again.
            else {
                operation.recover();
                if (attemptInterval > 0) {
                    try {
                        Thread.sleep(attemptInterval);
                    }
                    catch(InterruptedException ignore) {
                    }
                }
            }
        }

        // If the operation never succeeds, then
        // indicate this by throwing an exception.
        if (!succeeded)
            throw new RetryFailedException(
                "Maximum failed attempts was reached.");
    }
}

