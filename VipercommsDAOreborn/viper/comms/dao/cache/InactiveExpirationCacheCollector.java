package viper.comms.dao.cache;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InactiveExpirationCacheCollector
implements CacheCollector {

    private Map cache;
    private List keyAccessQueue;
    private long expirationInterval;

    public InactiveExpirationCacheCollector(
        Map cache,
        List keyAccessQueue,
        long expirationInterval) {

        this.cache = cache;
        this.keyAccessQueue = keyAccessQueue;
        this.expirationInterval = expirationInterval;
    }

    public void collect() {

        // Compute the expiration time.
        long expirationTime
            = System.currentTimeMillis()
            - expirationInterval;

        synchronized(cache) {
            synchronized(keyAccessQueue) {

                // Find the list of keys to remove.
                List keysToRemove = new LinkedList();
                for(Iterator i = keyAccessQueue.iterator();
                    i.hasNext(); ) {

                    Object key = i.next();
                    InactiveExpirationCacheEntry entry
                        = (InactiveExpirationCacheEntry)
                        cache.get(key);

                    if (entry.getLastAccessTime()
                        < expirationTime)
                        keysToRemove.add(key);
                    else
                        break;
                }

                // Remove the expired cache entries.
                for(Iterator i = keysToRemove.iterator();
                    i.hasNext(); ) {

                    Object key = i.next();
                    keyAccessQueue.remove(key);
                    cache.remove(key);
                }
            }
        }
    }
}

