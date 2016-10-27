package viper.comms.dao.conn;

import java.sql.*;
import java.util.*;


public class ConnectionPoolHandlerLateBinding implements IConnectionPool {
	
	String strDriver;
    // The contents of the connection pool.
    // This maps connection keys to lists of
    // available connections.
    private Map contents = new HashMap();
    private boolean IsUsingPooledConnection=true;
    public ConnectionPoolHandlerLateBinding(String dbaseDriver) {
		// TODO Auto-generated constructor stub
    	strDriver=dbaseDriver;
	}
    
    /* (non-Javadoc)
	 * @see siap.conn.IConnectionPool#getConnection(java.lang.String, java.lang.String, java.lang.String)
	 */
    public Connection getConnection(String url,
                                    String user,
                                    String password)
        throws SQLException {
    	Driver d = null;
    	 try {
//             
//             // Make the driver class available.
               Class.forName(strDriver);
         } catch (ClassNotFoundException ex) {
             ex.printStackTrace();
         }
//    	Driver d = new org.apache.derby.jdbc.EmbeddedDriver();
    	//Driver d=null;
    	//DriverManager.registerDriver (d);
        // Form the connection key.  For simplicity, this
        // example stores it as a string that incorporates
        // each of the connection parameters.
        StringBuffer buffer = new StringBuffer();
        buffer.append(url);
        buffer.append(":");
        buffer.append(user);
        buffer.append(":");
        buffer.append(password);
        Object connectionKey = buffer.toString();

        // Check to see if there is a matching
        // connection list in the pool.  If there is
        // not, create a new one.
        List connectionList;
        synchronized(contents) {
            connectionList
                = (List)contents.get(connectionKey);
            if (connectionList == null) {
                connectionList = new LinkedList();
                contents.put(connectionKey, connectionList);
            }
        }

        // Check to see if there is a connection in
        // the connection list.  If there is at least
        // one connection, remove it from the list.
        // Otherwise, create a new one.
        Connection connection;
        synchronized(connectionList) {
            if (connectionList.size() > 0) {
                connection
                    = (Connection)connectionList.remove(0);
            }
            else {
                connection
                    = DriverManager.getConnection(url,
                    user, password);
            }
        }

        // Decorate the connection with a PooledConnection.
        // This will cause it to be returned to the pool
        // when the client closes it.
        connection = new PooledConnection(this,
            connectionKey, connection);

        return connection;
    }

    // PooledConnection.close calls this.
    public void putConnection(Object connectionKey,
                       Connection connection) {

        // Resolve the connection list for the specified
        // connection key.
        List connectionList;
        synchronized(contents) {
            connectionList
                = (List)contents.get(connectionKey);
        }

        // Add the connection to the list.
        synchronized(connectionList) {
            connectionList.add(connection);
        }
    }

    public boolean isUsingPooledConnection() {
		return IsUsingPooledConnection;
	}

	public void setUsingPooledConnection(boolean isUsingPooledConnection) {
		IsUsingPooledConnection = isUsingPooledConnection;
	}
}


