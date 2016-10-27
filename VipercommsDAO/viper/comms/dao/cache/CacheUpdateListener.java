package viper.comms.dao.cache;

import java.util.EventListener;

public interface CacheUpdateListener
extends EventListener {

    void cacheEntryPut(CacheUpdateEvent event);
    void cacheEntryRemoved(CacheUpdateEvent event);
    void cacheEntriesCleared(CacheUpdateEvent event);

}