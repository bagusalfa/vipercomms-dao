package viper.comms.dao.cache;

import java.util.Map;

public class CacheReplicator
implements CacheUpdateListener {

    private Map cache;

    public CacheReplicator(Map cache) {
        this.cache = cache;
    }

    public void cacheEntryPut(
        CacheUpdateEvent event) {

        cache.put(event.getKey(), event.getValue());
    }

    public void cacheEntryRemoved(
        CacheUpdateEvent event) {

        cache.remove(event.getKey());
    }

    public void cacheEntriesCleared(
        CacheUpdateEvent event) {

        cache.clear();
    }
}