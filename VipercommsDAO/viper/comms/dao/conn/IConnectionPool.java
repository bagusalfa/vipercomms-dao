package viper.comms.dao.conn;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {
  
	/**
	 Gets a connection from the connection pool.
	 This creates a new connection if none are
	 available in the pool.
	 */
	public abstract Connection getConnection(String url, String user,
			String password) throws SQLException;
	public abstract void putConnection(Object connectionKey,
			Connection connection);
	public boolean isUsingPooledConnection() ;
	public void setUsingPooledConnection(boolean isUsingPooledConnection);
	
}