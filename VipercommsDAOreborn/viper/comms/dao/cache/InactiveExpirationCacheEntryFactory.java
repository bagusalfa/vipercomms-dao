package viper.comms.dao.cache;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class InactiveExpirationCacheEntry
implements CacheEntry {

    private List keyAccessQueue;
    private Object key;
    private Object data;
    private long lastAccessTime;

    InactiveExpirationCacheEntry(
        List keyAccessQueue,
        Object key,
        Object data) {

        this.keyAccessQueue = keyAccessQueue;
        this.key = key;
        this.data = data;
        lastAccessTime = System.currentTimeMillis();
    }

    public Object getData() {

        // Move the key to the front of the queue
        // to indicate that it was accessed.
        synchronized(keyAccessQueue) {
            keyAccessQueue.remove(key);
            keyAccessQueue.add(0, key);
        }

        lastAccessTime = System.currentTimeMillis();
        return data;
    }

    long getLastAccessTime() {
        return lastAccessTime;
    }
}
public class InactiveExpirationCacheEntryFactory
implements CacheEntryFactory {

    private List keyAccessQueue;

    public InactiveExpirationCacheEntryFactory(
        List keyAccessQueue) {

        this.keyAccessQueue = keyAccessQueue;
    }

    public CacheEntry newCacheEntry(Object key, Object data) {

        return new InactiveExpirationCacheEntry(
            keyAccessQueue,
            key,
            data);
    }

}

