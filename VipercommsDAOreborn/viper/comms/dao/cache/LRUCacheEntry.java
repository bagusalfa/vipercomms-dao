package viper.comms.dao.cache;

import java.util.List;

public class LRUCacheEntry implements CacheEntry {

    private List keyAccessQueue;
    private Object key;
    private Object data;

    LRUCacheEntry(
        List keyAccessQueue,
        Object key,
        Object data) {

        this.keyAccessQueue = keyAccessQueue;
        this.key = key;
        this.data = data;

        // Add the key to the front of the queue.
        synchronized(keyAccessQueue) {
            keyAccessQueue.add(0, key);
        }
    }

    public Object getData() {

        // Move the key to the front of the queue
        // to indicate that it was accessed.
        synchronized(keyAccessQueue) {
            keyAccessQueue.remove(key);
            keyAccessQueue.add(0, key);
        }

        return data;
    }
}