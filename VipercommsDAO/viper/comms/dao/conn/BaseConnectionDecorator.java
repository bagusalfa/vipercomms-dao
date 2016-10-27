package viper.comms.dao.conn;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

public class BaseConnectionDecorator
implements Connection {
	

    private Connection reference;

    public BaseConnectionDecorator(Connection reference) {
        this.reference = reference;
    }

    public void close() throws SQLException {
        reference.close();
    }

    public Statement createStatement() throws SQLException {
        return reference.createStatement();
    }

	public PreparedStatement prepareStatement(String arg0) throws SQLException {
		
		return reference.prepareStatement(arg0);
	}

	public CallableStatement prepareCall(String arg0) throws SQLException {
		
		return reference.prepareCall(arg0);
	}

	public String nativeSQL(String arg0) throws SQLException {
		
		return reference.nativeSQL(arg0);
	}

	public void setAutoCommit(boolean arg0) throws SQLException {
		
		reference.setAutoCommit(arg0);
	}

	public boolean getAutoCommit() throws SQLException {
		
		return reference.getAutoCommit();
	}

	public void commit() throws SQLException {
		
		reference.commit();
	}

	public void rollback() throws SQLException {
		
		reference.rollback();		
	}

	public boolean isClosed() throws SQLException {
		
		return reference.isClosed();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		
		return reference.getMetaData();
	}

	public void setReadOnly(boolean arg0) throws SQLException {
		
		reference.setReadOnly(arg0);
	}

	public boolean isReadOnly() throws SQLException {
		
		return reference.isReadOnly();
	}

	public void setCatalog(String arg0) throws SQLException {
		
		reference.setCatalog(arg0);	
	}

	public String getCatalog() throws SQLException {
		
		return reference.getCatalog();
	}

	public void setTransactionIsolation(int arg0) throws SQLException {
		
		reference.setTransactionIsolation(arg0);	
	}

	public int getTransactionIsolation() throws SQLException {
		
		return reference.getTransactionIsolation();
	}

	public SQLWarning getWarnings() throws SQLException {
		
		return reference.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		
		reference.clearWarnings();
	}

	public Statement createStatement(int arg0, int arg1) throws SQLException {
		
		return reference.createStatement(arg0,arg1);
	}

	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
		
		return reference.prepareStatement(arg0,arg1,arg2);
	}

	public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
		
		return reference.prepareCall(arg0,arg1,arg2);
	}

	public Map<String, Class<?>> getTypeMap() throws SQLException {
		
		return reference.getTypeMap();
	}

	public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
		
		reference.setTypeMap(arg0);
	}

	public void setHoldability(int arg0) throws SQLException {
		
		reference.setHoldability(arg0);		
	}

	public int getHoldability() throws SQLException {
		
		return reference.getHoldability();
	}

	public Savepoint setSavepoint() throws SQLException {
		
		return reference.setSavepoint();
	}

	public Savepoint setSavepoint(String arg0) throws SQLException {
		
		return reference.setSavepoint(arg0);
	}

	public void rollback(Savepoint arg0) throws SQLException {
		
		reference.rollback();
	}

	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		
		reference.releaseSavepoint(arg0);	
	}

	public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
		
		return reference.createStatement(arg0,arg1,arg2);
	}

	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		
		return reference.prepareStatement(arg0,arg1,arg2,arg3);
	}

	public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		
		return reference.prepareCall(arg0,arg1,arg2,arg3);
	}

	public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
		
		return reference.prepareStatement(arg0,arg1);
	}

	public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
		
		return reference.prepareStatement(arg0,arg1);
	}

	public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
		
		return reference.prepareStatement(arg0,arg1);
	}

	public Clob createClob() throws SQLException {
		
		return ((BaseConnectionDecorator) reference).createClob();
	}

	public Blob createBlob() throws SQLException {
		
		return ((BaseConnectionDecorator) reference).createBlob();
	}
public boolean isValid(int timeout) throws SQLException {
		
		return false;
	}
	

	public void setClientInfo(String name, String value){
		
		((BaseConnectionDecorator) reference).setClientInfo(name, value);
	}

	public void setClientInfo(Properties properties)  {
		
((BaseConnectionDecorator) reference).setClientInfo(properties);		
	}

	public String getClientInfo(String name) throws SQLException {
		
		return ((BaseConnectionDecorator) reference).getClientInfo(name);
	}

	public Properties getClientInfo() throws SQLException {
		
		return ((BaseConnectionDecorator) reference).getClientInfo();
	}



	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		
		return ((BaseConnectionDecorator) reference).createArrayOf(typeName,elements);
	}

	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		
		return ((BaseConnectionDecorator) reference).createStruct(typeName,attributes);
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		
		return ((BaseConnectionDecorator) reference).unwrap(iface);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		
		return ((BaseConnectionDecorator) reference).isWrapperFor(iface);
	}

	

	

//    // Repeat for the rest of the operations
//    // that the Connection interface defines.
//    // ...
	public NClob createNClob() throws SQLException {
		
		return reference.createNClob();
	}

	public SQLXML createSQLXML() throws SQLException {
		
		return reference.createSQLXML();
	}

}



