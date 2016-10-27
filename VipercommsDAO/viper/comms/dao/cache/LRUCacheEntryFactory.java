package viper.comms.dao.cache;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class LRUCacheEntryFactory
implements CacheEntryFactory {

    private List keyAccessQueue;

    public LRUCacheEntryFactory(List keyAccessQueue) {
        this.keyAccessQueue = keyAccessQueue;
    }

    public CacheEntry newCacheEntry(Object key, Object data) {
        return new LRUCacheEntry(keyAccessQueue, key, data);
    }
}


