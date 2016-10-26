package viper.comms.dao.resource.decorator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

public class BaseStatementDecorator
implements Statement {

    private Statement reference;

    public BaseStatementDecorator(Statement reference) {
        this.reference = reference;
    }

    public void close() throws SQLException {
        reference.close();
    }

    public ResultSet executeQuery(String sql)
    throws SQLException {
        return reference.executeQuery(sql);
    }

	public int executeUpdate(String arg0) throws SQLException {
		
		return reference.executeUpdate(arg0);
	}

	public int getMaxFieldSize() throws SQLException {
		
		return reference.getMaxFieldSize();
	}

	public void setMaxFieldSize(int arg0) throws SQLException {
		
		reference.setMaxFieldSize(arg0);
	}

	public int getMaxRows() throws SQLException {
		
		return reference.getMaxRows();
	}

	public void setMaxRows(int arg0) throws SQLException {
		
		reference.setMaxRows(arg0);
	}

	public void setEscapeProcessing(boolean arg0) throws SQLException {
		
		reference.setEscapeProcessing(arg0);
	}

	public int getQueryTimeout() throws SQLException {
		
		return reference.getQueryTimeout();
	}

	public void setQueryTimeout(int arg0) throws SQLException {
		
		reference.setQueryTimeout(arg0);		
	}

	public void cancel() throws SQLException {
		
		reference.cancel();	
	}

	public SQLWarning getWarnings() throws SQLException {
		
		return reference.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		
		reference.clearWarnings();	
	}

	public void setCursorName(String arg0) throws SQLException {
		
		reference.setCursorName(arg0);	
	}

	public boolean execute(String arg0) throws SQLException {
		
		return reference.execute(arg0);
	}

	public ResultSet getResultSet() throws SQLException {
		
		return reference.getResultSet();
	}

	public int getUpdateCount() throws SQLException {
		
		return reference.getUpdateCount();
	}

	public boolean getMoreResults() throws SQLException {
		
		return reference.getMoreResults();
	}

	public void setFetchDirection(int arg0) throws SQLException {
		
		reference.setFetchDirection(arg0);
	}

	public int getFetchDirection() throws SQLException {
		
		return reference.getFetchDirection();
	}

	public void setFetchSize(int arg0) throws SQLException {
		
		reference.setFetchSize(arg0);	
	}

	public int getFetchSize() throws SQLException {
		
		return reference.getFetchSize();
	}

	public int getResultSetConcurrency() throws SQLException {
		
		return reference.getResultSetConcurrency();
	}

	public int getResultSetType() throws SQLException {
		
		return reference.getResultSetType();
	}

	public void addBatch(String arg0) throws SQLException {
		
		reference.addBatch(arg0);
	}

	public void clearBatch() throws SQLException {
		
		reference.clearBatch();
	}

	public int[] executeBatch() throws SQLException {
		
		return reference.executeBatch();
	}

	public Connection getConnection() throws SQLException {
		
		return reference.getConnection();
	}

	public boolean getMoreResults(int arg0) throws SQLException {
		
		return reference.getMoreResults();
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		
		return reference.getGeneratedKeys();
	}

	public int executeUpdate(String arg0, int arg1) throws SQLException {
		
		return reference.executeUpdate(arg0,arg1);
	}

	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		
		return reference.executeUpdate(arg0,arg1);
	}

	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		
		return reference.executeUpdate(arg0,arg1);
	}

	public boolean execute(String arg0, int arg1) throws SQLException {
		
		return reference.execute(arg0,arg1);
	}

	public boolean execute(String arg0, int[] arg1) throws SQLException {
		
		return reference.execute(arg0,arg1);
	}

	public boolean execute(String arg0, String[] arg1) throws SQLException {
		
		return reference.execute(arg0,arg1);
	}

	public int getResultSetHoldability() throws SQLException {
		
		return reference.getResultSetHoldability();
	}

	public boolean isClosed() throws SQLException {
		
		return reference.isClosed();
	}

	public void setPoolable(boolean poolable) throws SQLException {
		
		reference.setPoolable(poolable);
	}

	public boolean isPoolable() throws SQLException {
		
		return reference.isPoolable();
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		
		return reference.unwrap(iface);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		
		return reference.isWrapperFor(iface);
	}

    // Repeat for the rest of the operations
    // that the Statement interface defines.
    // ...

    public void closeOnCompletion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isCloseOnCompletion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
