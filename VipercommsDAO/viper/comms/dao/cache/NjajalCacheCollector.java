package viper.comms.dao.cache;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NjajalCacheCollector {
	public static void main(String[] args) {
//		 Initialize and plug in the LRU collection strategy.
		List keyAccessQueue = new LinkedList();

		Map cache = null;
		CacheCollector cacheCollector = new LRUCacheCollector(
		    cache,
		    keyAccessQueue,
		    1000); // queueSizeThreshold = 1000

		CacheEntryFactory cacheEntryFactory
		    = new LRUCacheEntryFactory(keyAccessQueue);

		CacheAccessor cacheAccessor = new CacheAccessor(
		    cache,
		    60000, // collectionInterval = 1 minute
		    cacheCollector,
		    cacheEntryFactory);


	}
}
