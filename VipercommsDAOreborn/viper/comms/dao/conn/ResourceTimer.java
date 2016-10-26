package viper.comms.dao.conn;
public class ResourceTimer {

    private ResourceTimerListenerList listenerList;
    private ResourceTimerThread thread = null;
    private boolean started = false;

    /**
    Constructs a ResourceTimer object.
    */
    ResourceTimer(long inactivityThreshold) {
        listenerList = new ResourceTimerListenerList(this);
        thread = new ResourceTimerThread(inactivityThreshold,
            listenerList);
    }

    /**
    Registers a ResourceTimerListener to be notified
    when a resource remains inactive beyond the
    configured inactivity threshold.
    */
    void addResourceTimerListener(
        ResourceTimerListener listener) {
        listenerList.addResourceTimerListener(listener);
    }

    /**
    Deregisters a ResourceTimerListener to be notified
    when a resource remains inactive beyond the
    configured inactivity threshold.
    */
    void removeResourceTimerListener(
        ResourceTimerListener listener) {
        listenerList.removeResourceTimerListener(listener);
    }

    /**
    Starts the resource timer.
    */
    synchronized void start() {
        if (!started) {
            started = true;
            thread.start();
        }
    }

    /**
    Stops the resource timer.
    */
    synchronized void stop() {
        if (started) {
            started = false;
            thread.safeStop();
        }
    }

    /**
    Resets the resource timer.
    */
    synchronized void reset() {
        thread.reset();
    }
}

