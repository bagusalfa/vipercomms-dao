package viper.comms.dao.conn;

import java.util.EventObject;



public class ResourceTimerEvent
extends EventObject {

    ResourceTimerEvent(ResourceTimer eventSource) {
        super(eventSource);
    }

}