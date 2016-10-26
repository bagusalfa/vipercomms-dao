package viper.comms.dao.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import viper.comms.dao.conn.DataAccessor;
import viper.comms.dao.exception.DataException;

public class CacheAccessor {


	private DataAccessor dataAccessor;
	private String table;

	  private PersistenceManager persistenceManager;
	    private Class clas;
	    private Map cache;
	    private Set primedKeys;
	    private KeyFactory keyFactory;

	    public CacheAccessor(
	        PersistenceManager persistenceManager,
	        Class clas,
	        Map cache,
	        Set primedKeys,
	        KeyFactory keyFactory) {

	        this.persistenceManager = persistenceManager;
	        this.clas = clas;
	        this.cache = cache;
	        this.primedKeys = primedKeys;
	        this.keyFactory = keyFactory;
	    }

	    /**
	    Primes the cache with all entries that correspond
	    to the specified partial key.

	    @param partialKey The partial key.
	    */
	    public void prime(Key partialKey) {

	        // Avoid priming the same key twice.
	        // This enables the caller to prime repeatedly
	        // without imposing superfluous data access.
	        if (primedKeys.contains(partialKey))
	            return;

	        // Read the physical data that corresponds
	        // to the partial key.
	        Query query = persistenceManager.newQuery(
	            clas, partialKey.toString());
	        Collection data = (Collection)query.execute();

	        // For each domain object that matches the partial
	        // key, use the KeyFactory to generate a specific
	        // key.  Use the specific keys to store the data
	        // in the cache.
	        for(Iterator i = data.iterator(); i.hasNext(); ) {

	            Object domainObject = i.next();
	            Key specificKey
	                = keyFactory.newSpecificKey(domainObject);

	            // If the cache already contains a list for this
	            // specific key, then add to the list.  Otherwise,
	            // initialize a new list.
	            List cachedData = (List)cache.get(specificKey);
	            if (cachedData == null) {
	                cachedData = new LinkedList();
	                cache.put(specificKey, cachedData);
	            }

	            cachedData.add(domainObject);
	        }

	        // Remember that this partial key has been primed.
	        primedKeys.add(partialKey);
	    }

	
	
    public CacheAccessor(Map cache) {
        this.cache = cache;
    }

    public CacheAccessor(
            DataAccessor dataAccessor,
            String table,
            Map cache) {

            this.dataAccessor = dataAccessor;
            this.table = table;
            this.cache = cache;
        }

	/**
    Reads data from the cache.

    @param key  The key.
    @return     The data.
    **/
    public List read(Key key) {

        List data = (List)cache.get(key);

        // If no data is found, return an empty
        // list.
        if (data == null)
            data = Collections.EMPTY_LIST;

        return data;
    }
    /**
    Reads data from the cache.

    @param key  The key.
    @return     The data.
    **/
    public List readDA(Key key) throws DataException {

        List data = (List)cache.get(key);

        // If the data that the caller requests
        // is not found in the cache, then read it
        // from the database.
        if (data == null) {
            data = dataAccessor.read(table, key);

            // Store the data in the cache so that it is
            // immediately accessible the next time a
            // caller requests it.  If no matching data
            // is found, then store an empty list in the
            // cache.
            cache.put(key, data);
        }

        return data;
    }
    
   
    private long collectionInterval;
    private CacheCollector cacheCollector;
    private CacheEntryFactory cacheEntryFactory;
    private CollectorThread collectorThread;

    public CacheAccessor(
        Map cache,
        long collectionInterval,
        CacheCollector cacheCollector,
        CacheEntryFactory cacheEntryFactory) {

        this.cache = cache;
        this.collectionInterval = collectionInterval;
        this.cacheCollector = cacheCollector;
        this.cacheEntryFactory = cacheEntryFactory;

        // Start the collector thread.
        Thread collectorThread
            = new CollectorThread();
        collectorThread.start();
    }

    /**
    When this object is no longer needed, stop the
    collector thread.
    */
    protected void finalize() throws Throwable {
        collectorThread.safeStop();
    }

    /**
    Reads data from the cache.  This returns
    null if the key the caller requests does
    not exist in the cache.
    **/
    public Object read(Object key) {
        synchronized(cache) {
            CacheEntry entry = (CacheEntry)cache.get(key);
            if (entry != null)
                return entry.getData();
            else
                return null;
        }
    }

    /**
    Writes data to the cache.
    */
    public void write(Object key, Object data) {
        CacheEntry entry
            = cacheEntryFactory.newCacheEntry(key, data);
        synchronized(cache) {
            cache.put(key, entry);
        }
    }

    /**
    The cache collector thread, which repeatedly
    invokes the collect operation.
    */
    private class CollectorThread extends Thread {

        private boolean done = false;

        CollectorThread() {
            setDaemon(true);
        }

        public void run() {
            sleep();
            while(!done) {
                cacheCollector.collect();
                sleep();
            }
        }

        private void sleep() {
            try  {
                Thread.sleep(collectionInterval);
            }
            catch(InterruptedException ignore) {
            }
        }
        void safeStop() {
            done = true;
            this.interrupt();
        }
    }


    private CollectionStatistics statistics
        = new CollectionStatistics("Generic cache",
        cache.keySet());

    /**
    Returns the cache statistics.
    */
    public CurrentStatistics getStatistics() {
        return statistics;
    }

    /**
    Reads data from the cache.

    @param key  The key.
    @return     The data.
    **/
    public List readStat(Object key) {

        List data = (List)cache.get(key);

        // If the value was found in the cache,
        // then mark this operation as a hit.
        if (data != null) {
            statistics.markHit();
        }

        // Otherwise, mark this operation as a miss
        // and return an empty list.
        else {
            statistics.markMiss();
            data = Collections.EMPTY_LIST;
        }

        return data;
    }

    
}

