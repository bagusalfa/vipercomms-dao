package viper.comms.dao.cache;

import java.util.EventObject;

public class CacheUpdateEvent
extends EventObject {

    private Object key;
    private Object value;

    public CacheUpdateEvent(
        Object source,
        Object key,
        Object value) {

        super(source);
        this.key = key;
        this.value = value;
    }

    public CacheUpdateEvent(
        Object source,
        Object key) {

        super(source);
        this.key = key;
        this.value = null;
    }

    public CacheUpdateEvent(Object source) {
        super(source);
        this.key = null;
        this.value = null;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
