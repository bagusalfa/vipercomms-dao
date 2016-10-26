package viper.comms.dao.conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import viper.comms.dao.exception.RetryFailedException;
import viper.comms.dao.resource.pool.ConnectionPool;

public class QueryOperation implements Retryable {
private Statement localStatement;
private String sqlStatement;
ResultSet resultSet;

	/*
    // Private variables that are input to the
    // query operation.
    private ConnectionPool connectionPool;
    private String url;
    private String user;
    private String password;
    private String sqlStatement;

    // Private variables that are resources
    // created for the query operation.
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public QueryOperation(ConnectionPool connectionPool,
                          String url,
                          String user,
                          String password,
                          String sqlStatement) {

        this.connectionPool = connectionPool;
        this.url = url;
        this.user = user;
        this.password = password;
        this.sqlStatement = sqlStatement;
    }
*/
	public QueryOperation(Statement statement,String sqlStatement){
		localStatement=statement;
	}
	
    public boolean attempt() throws RetryFailedException {

        try {
//            connection = connectionPool.getConnection(url,
//                user, password);
//            statement = connection.createStatement();
            resultSet = localStatement.executeQuery(sqlStatement);
            return true;
        }
        catch(SQLException e) {

            // If the connection is stale, then return false
            // to indicate that the operation failed but
            // can be tried again with a new connection.
            // This is likely to be a highly database driver-
            // dependent test, so if you support multiple
            // databases, consider building this check into
            // an instance of the Resource Descriptor pattern.
            if (e.getSQLState().equals("08003"))
                return false;

            // Throw a RetryFailedException for any other
            // errors.  Retrying is not likely to remedy
            // them.
            else
                throw new RetryFailedException(
                    "Query operation attempt error", e);
        }
    }

    /**
    Returns the result set after the operation runs
    successfully.
    */
    public ResultSet getResultSet() {
        return resultSet;
    }


    /**
    Closes the resources held by this operation, if any.
    You need to leave resources open until you are done reading
    the result set.
    */
    public void close() throws SQLException {
        if (resultSet != null)
            resultSet.close();
        if (localStatement != null)
        	localStatement.close();
//        if (connection != null && !connection.isClosed())
//            connection.close();
    }

	public void recover() {
		// TODO Auto-generated method stub
		System.out.println("It should be recover Operation");
	}

}

