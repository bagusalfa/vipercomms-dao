package viper.comms.dao.cache;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FixedExpirationCacheCollector
implements CacheCollector {

    private Map cache;
    private List keyCreationQueue;
    private long expirationInterval;

    public FixedExpirationCacheCollector(
        Map cache,
        List keyCreationQueue,
        long expirationInterval) {

        this.cache = cache;
        this.keyCreationQueue = keyCreationQueue;
        this.expirationInterval = expirationInterval;
    }

    public void collect() {

        // Compute the expiration time.
        long expirationTime
            = System.currentTimeMillis()
            - expirationInterval;

        synchronized(cache) {
            synchronized(keyCreationQueue) {

                // Find the list of keys to remove.
                List keysToRemove = new LinkedList();
                for(Iterator i = keyCreationQueue.iterator();
                    i.hasNext(); ) {

                    Object key = i.next();
                    FixedExpirationCacheEntry entry
                        = (FixedExpirationCacheEntry)
                        cache.get(key);

                    if (entry.getCreationTime()
                        < expirationTime)
                        keysToRemove.add(key);
                    else
                        break;
                }

                // Remove the expired cache entries.
                for(Iterator i = keysToRemove.iterator();
                    i.hasNext(); ) {

                    Object key = i.next();
                    keyCreationQueue.remove(key);
                    cache.remove(key);
                }
            }
        }
    }
}

