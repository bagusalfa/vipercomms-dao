package viper.comms.dao.cache;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReplicatedCache implements Map {

    private Map cache;
    private List listeners = new LinkedList();

    public ReplicatedCache(Map cache) {
        this.cache = cache;
    }

    /**
    Adds a cache update listener to be notified
    when any cache update operation occurs.
    */
    public void addCacheUpdateListener(
        CacheUpdateListener listener) {
        listeners.add(listener);
    }

    /**
    Removes a cache update listener.
    */
    public void removeCacheUpdateListener(
        CacheUpdateListener listener) {
        listeners.remove(listener);
    }

    /**
    Puts a new cache entry and notifies all
    cache update listeners.
    */
    public Object put(Object key, Object value) {

        // Delegate the put operation to the
        // underlying cache.
        Object previousValue = cache.put(key, value);

        // Notify all cache update listeners.
        CacheUpdateEvent event
            = new CacheUpdateEvent(this, key, value);
        for(Iterator i = listeners.iterator();
            i.hasNext(); ) {

            ((CacheUpdateListener)i.next())
                .cacheEntryPut(event);
        }

        return previousValue;
    }

    /**
    Puts all entries from a Map into the cache and
    notifies all cache update listeners.
    */
    public void putAll(Map otherMap)
    {
        // Iterate through the map, calling put for
        // each entry.  The put operation takes care of the
        // rest.
        for(Iterator i = otherMap.entrySet().iterator();
            i.hasNext(); )  {

            Map.Entry mapEntry = (Map.Entry)i.next();
            this.put(mapEntry.getKey(), mapEntry.getValue());
        }
    }

    /**
    Removes a cache entry and notifies all cache update
    listeners.
    */
    public Object remove(Object key) {

        // Delegate the remove operation to the cache.
        Object previousValue = cache.remove(key);

        // Notify all cache update listeners.
        CacheUpdateEvent event
            = new CacheUpdateEvent(this, key);
        for(Iterator i = listeners.iterator();
            i.hasNext(); ) {

            ((CacheUpdateListener)i.next())
                .cacheEntryRemoved(event);
        }

        return previousValue;
    }

    /**
    Clears the cache's contents and notifies all cache update
    listeners.
    */
    public void clear() {

        // Delegate the clear operation to the cache.
        cache.clear();

        // Notify all cache update listeners.
        CacheUpdateEvent event = new CacheUpdateEvent(this);
        for(Iterator i = listeners.iterator();
            i.hasNext(); ) {

            ((CacheUpdateListener)i.next())
                .cacheEntriesCleared(event);
        }
    }

    //
    // Implement the rest of the java.util.Map operations
    // by simply delegating to the cache's contents.
    //

    public boolean containsKey(Object key) {
        return cache.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return cache.containsValue(value);
    }

    public Set entrySet() {
        return cache.entrySet();
    }

    public boolean equals(Object other) {
        if (!(other instanceof ReplicatedCache))
            return false;
        ReplicatedCache otherReplicatedCache
            = (ReplicatedCache)other;
        return otherReplicatedCache.cache.equals(
            this.cache);
    }

    public Object get(Object key) {
        return cache.get(key);
    }

    public int hashCode() {
        return 37 * cache.hashCode();
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }

    public Set keySet() {
        return cache.keySet();
    }

    public int size() {
        return cache.size();
    }

    public Collection values() {
        return cache.values();
    }
}

