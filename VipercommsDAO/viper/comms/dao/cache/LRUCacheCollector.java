package viper.comms.dao.cache;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LRUCacheCollector
implements CacheCollector
{
    private Map cache;
    private List keyAccessQueue;
    private int queueSizeThreshold;

    public LRUCacheCollector(
        Map cache,
        List keyAccessQueue,
        int queueSizeThreshold) {

        this.cache = cache;
        this.keyAccessQueue = keyAccessQueue;
        this.queueSizeThreshold = queueSizeThreshold;
    }

    public void collect() {
        synchronized(cache) {
            synchronized(keyAccessQueue) {

                if (keyAccessQueue.size()
                    > queueSizeThreshold) {

                    // Get a list of keys that correspond
                    // to the least recently used entries.
                    // These are the ones at the back of
                    // the queue.
                    List keysToRemove
                        = keyAccessQueue.subList(
                        queueSizeThreshold,
                        keyAccessQueue.size());

                    // Remove the keys from the cache's
                    // contents.
                    for(Iterator i = keysToRemove.iterator();
                        i.hasNext(); )
                        cache.remove(i.next());

                    // Remove the keys from the queue.
                    keysToRemove.clear();
                }
            }
        }
    }
}
