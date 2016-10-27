package viper.comms.dao.cache;
public interface CacheCollector {

    /**
    Make a single pass through the cache,
    collecting expired entries where possible.
    **/
    void collect();
}