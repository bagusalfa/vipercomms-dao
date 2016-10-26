package viper.comms.dao.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import viper.comms.dao.conn.ConcreteDataAccessor;
import viper.comms.dao.conn.DataAccessor;
import viper.comms.dao.exception.DataException;

public class NjajalCache2 {

	/**
	 * @param args
	 * @throws DataException 
	 * @throws DataFormatException 
	 */
	public static void main(String[] args) throws DataFormatException, DataException {
		// TODO Auto-generated method stub
//		 Initialize the cache.
		Map cache = new HashMap();

//		 Initialize the DataAccessor implementation.
		DataAccessor dataAccessor
		    = new ConcreteDataAccessor();

//		 Initialize the CacheAccessor.
		CacheAccessor cacheAccessor
		    = new CacheAccessor(
		    dataAccessor,
		    "PREFERENCES",
		    cache);

//		This is some application code that uses the CacheAccessor to read user preferences for a specific user:

		Key key = new Key();
		key.set("USER_NAME", "george");

		List data = cacheAccessor.read(key);

	}

}
