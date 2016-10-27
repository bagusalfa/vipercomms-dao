package viper.comms.dao.cache;

import java.util.HashMap;
import java.util.Map;

public class NjajalReplicatedCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 Initialize the central cache.
		ReplicatedCache centralCache
		    = new ReplicatedCache(new HashMap());
//		 Initialize five replicated caches.
		ReplicatedCache[] replicatedCaches = new ReplicatedCache[5];
		for(int i = 0; i < replicatedCaches.length; ++i)
		{
		    Map cache = new HashMap();
		    replicatedCaches[i] = new ReplicatedCache(cache);

		    // Attach a cache replicator so that the replication
		    // cache notifies the central cache of all updates
		    // to it.
		    replicatedCaches[i].addCacheUpdateListener(
		        new CacheReplicator(centralCache));

		    // Attach a cache replicator so that the central
		    // cache notifies this cache when other
		    // replicated caches are updated.
		    centralCache.addCacheUpdateListener(
		        new CacheReplicator(cache));
		}


	}

}
