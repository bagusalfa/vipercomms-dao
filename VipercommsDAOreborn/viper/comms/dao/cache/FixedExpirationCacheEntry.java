package viper.comms.dao.cache;
public class FixedExpirationCacheEntry
implements CacheEntry {

    private Object data;
    private long creationTime;

    FixedExpirationCacheEntry(Object data)
    {
        this.data = data;
        creationTime = System.currentTimeMillis();
    }

    public Object getData()
    {
        return data;
    }

    long getCreationTime()
    {
        return creationTime;
    }
}