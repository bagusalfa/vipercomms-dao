package viper.comms.dao.cache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import viper.comms.dao.conn.Row;

public class NjajalCache {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
//		 Initialize the cache.
		Map cache = new HashMap();

Statement statement = null;
		//		 Populate the cache with all the user
//		 preference data.
		ResultSet resultSet
		    =  statement.executeQuery("SELECT * FROM PREFERENCES");

		while(resultSet.next()) {

		    String userName = resultSet.getString("USER_NAME");
		    String locale = resultSet.getString("LOCALE");
		    int gridSize = resultSet.getInt("GRID_SIZE");
		    String background = resultSet.getString("BACKGROUND");

		    Key key = new Key();
		    key.set("USER_NAME", userName);

		    Row row = new Row();
		    row.addColumn("USER_NAME", userName);
		    row.addColumn("LOCALE", locale);
		    row.addColumn("GRID_SIZE", new Integer(gridSize));
		    row.addColumn("BACKGROUND", background);

		    List data = new LinkedList();
		    data.add(row);

		    cache.put(key, data);
		    

//			 Initialize the CacheAccessor.
			CacheAccessor cacheAccessor = new CacheAccessor(cache);

//			This is some sample application code that uses the CacheAccessor to read user preferences for a specific user:

			key.set("USER_NAME", "george");

			List<Row> data1 = cacheAccessor.read(key);

		    
		    
		    
		}
		try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
