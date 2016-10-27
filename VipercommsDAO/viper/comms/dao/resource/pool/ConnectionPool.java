package viper.comms.dao.resource.pool;

import java.sql.*;
import java.util.*;

public class ConnectionPool {

    // The contents of the connection pool.
    // This maps connection keys to lists of
    // available connections.
    private Map<Object, List> contents = new HashMap<Object, List>();

    /**
    Gets a connection from the connection pool.
    This creates a new connection if none are
    available in the pool.
    */
    public Connection getConnection(String url,
                                    String user,
                                    String password)
        throws SQLException {
//    	 try {
//             
//             // Make the driver class available.
//             Class.forName("org.firebirdsql.jdbc.FBDriver");
//         } catch (ClassNotFoundException ex) {
//             ex.printStackTrace();
//         }
    	Driver d = new org.firebirdsql.jdbc.FBDriver ();
    	//Driver d=null;
    	DriverManager.registerDriver (d);
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
    @SuppressWarnings("unchecked")
	void putConnection(Object connectionKey,
                       Connection connection) {

        // Resolve the connection list for the specified
        // connection key.
        List<Connection> connectionList;
        synchronized(contents) {
            connectionList
                = (List)contents.get(connectionKey);
        }

        // Add the connection to the list.
        synchronized(connectionList) {
            connectionList.add(connection);
        }
    }
}


