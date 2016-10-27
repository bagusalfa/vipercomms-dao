package viper.comms.dao.conn;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class ResourceTimerListenerList
{
    private List contents = new LinkedList();
    private ResourceTimer eventSource;

    /**
    Constructs a ResourceTimerListenerList object.
    */
    ResourceTimerListenerList(ResourceTimer eventSource) {
        this.eventSource = eventSource;
    }

    /**
    Registers a ResourceTimerListener to be notified
    when a resource remains inactive beyond the
    configured inactivity threshold.
    */
    void addResourceTimerListener(
        ResourceTimerListener listener) {

        synchronized(contents) {
            contents.add(listener);
        }
    }

    /**
    Deregisters a ResourceTimerListener so that it is no
    longer notified when a resource remains inactive
    beyond the configured inactivity threshold.
    */
    void removeResourceTimerListener(
        ResourceTimerListener listener) {

        synchronized(contents) {
            contents.remove(listener);
        }
    }

    /**
    Notifies all registered ResourceTimerListeners
    when a resource remains inactive beyond the
    configured inactivity threshold.
    */
    void fireResourceInactiveEvent() {

        // Make a copy of the listener list so that
        // we do not send notifications within a
        // synchronized block.  If we do not do this,
        // any listeners that attempt to deregister
        // themselves cause deadlock.
        List contentsClone;
        synchronized(contents) {
            contentsClone = new LinkedList(contents);
        }

        // Send the event to each registered listener.
        ResourceTimerEvent event
            = new ResourceTimerEvent(eventSource);
        for(Iterator i = contentsClone.iterator();
            i.hasNext(); ) {
            ResourceTimerListener listener
                = (ResourceTimerListener)i.next();
            listener.resourceInactive(event);
        }
    }
}

