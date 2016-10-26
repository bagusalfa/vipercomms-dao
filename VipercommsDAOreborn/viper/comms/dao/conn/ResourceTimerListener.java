package viper.comms.dao.conn;

import java.util.EventListener;

public interface ResourceTimerListener
extends EventListener {

    /**
    This method is called when a resource
    remains inactive beyond the configured
    inactivity threshold.
    */
    void resourceInactive(ResourceTimerEvent event);
}

