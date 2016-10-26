package viper.comms.dao.cache;
public interface CacheEntryFactory {

    /**
    Returns a new CacheEntry object.
    */
    CacheEntry newCacheEntry(
        Object key,
        Object data);
}