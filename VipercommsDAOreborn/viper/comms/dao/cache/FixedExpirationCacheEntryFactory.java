package viper.comms.dao.cache;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FixedExpirationCacheEntryFactory
implements CacheEntryFactory {

    private List keyCreationQueue;

    public FixedExpirationCacheEntryFactory(
        List keyCreationQueue) {

        this.keyCreationQueue = keyCreationQueue;
    }

    public CacheEntry newCacheEntry(
        Object key,
        Object data) {

        // Add this key to the front of the list.
        // If it already exists, remove the old
        // entry, since the corresponding cache
        // entry is replaced as well.
        synchronized(keyCreationQueue) {
            keyCreationQueue.remove(key);
            keyCreationQueue.add(key);
        }

        return new FixedExpirationCacheEntry(data);
    }

}

