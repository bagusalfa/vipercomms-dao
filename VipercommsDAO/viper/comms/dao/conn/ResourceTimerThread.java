package viper.comms.dao.conn;
class ResourceTimerThread
extends Thread
{
    private long inactivityThreshold;
    private ResourceTimerListenerList listenerList;

    private boolean reset = false;
    private boolean stop = false;
    private Object signal = new Object();

    /**
    Constructs a ResourceTimerThread object.
    */
    ResourceTimerThread(long inactivityThreshold,
        ResourceTimerListenerList listenerList) {

        this.inactivityThreshold = inactivityThreshold;
        this.listenerList = listenerList;

        // Make this a daemon thread.  This indicates that
        // the JVM may safely end even when
        // this thread is still running.
        setDaemon(true);
    }

    /**
    The main thread task.
    */
    public void run() {

        // Continue to loop as long as the thread
        // has not been stopped.
        while(!stop) {
            try {
                synchronized(signal) {

                    // Wait for the allowed period of
                    // inactivity.
                    signal.wait(inactivityThreshold);

                    // If the timer was reset, just loop
                    // again.
                    if (reset) {
                        reset = false;
                    }

                    // If the timer was not reset or stopped,
                    // then the resource has
                    // been inactive longer then the specified
                    // threshold.  Send an event to indicate
                    // this condition and stop this thread.
                    else if (!stop) {
                        listenerList
                            .fireResourceInactiveEvent();
                        stop = true;
                    }
                }
            }
            catch(InterruptedException ignore) {
                // If the wait is interrupted, simply loop
                // again.
            }
        }
    }

    /**
    Resets the timer thread to start measuring inactivity
    again.
    */
    void reset() {
        synchronized(signal) {
            reset = true;
            signal.notify();
        }
    }

    /**
    Stops the timer thread.
    */
    void safeStop() {
        synchronized(signal) {
            stop = true;
            signal.notify();
        }
    }
}

