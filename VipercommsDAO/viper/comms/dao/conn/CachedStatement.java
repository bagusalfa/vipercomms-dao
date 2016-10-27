package viper.comms.dao.conn;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

import viper.comms.dao.conn.StatementCachingConnection;


public class CachedStatement
extends BaseStatementDecorator
implements PreparedStatement {

    private StatementCachingConnection connection;
    private Object statementKey;
    private Statement reference;
    private boolean closed = false;

    
    public CachedStatement(
        StatementCachingConnection connection,
        Object statementKey,
        PreparedStatement reference) {

        super(reference);
        this.connection = connection;
        this.statementKey = statementKey;
        this.reference = reference;
    }

 

	/**
    Do not really close the reference statement.  Instead,
    just return it to the cache.
    **/
    public void close() throws SQLException {
        if (!closed) {
            closed = true;
            connection.putStatement(
                statementKey, (PreparedStatement) reference);
        }
    }


    public ResultSet executeQuery()
    throws SQLException {
        if (closed)
            throw new SQLException("Statement is closed.");
        return super.executeQuery(null);
    }

	public void addBatch() throws SQLException {
		((PreparedStatement)reference).addBatch();
	}

	public void clearParameters() throws SQLException {
		((PreparedStatement)reference).clearParameters();
	}

	public boolean execute() throws SQLException {
		return ((PreparedStatement)reference).execute();
	}

	public int executeUpdate() throws SQLException {
		return ((PreparedStatement)reference).executeUpdate();
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return ((PreparedStatement)reference).getMetaData();
	}

	public ParameterMetaData getParameterMetaData() throws SQLException {
		return ((PreparedStatement)reference).getParameterMetaData();
	}

	public void setArray(int parameterIndex, Array x) throws SQLException {
		((PreparedStatement)reference).setArray(parameterIndex, x);
	}

	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		((PreparedStatement)reference).setAsciiStream(parameterIndex, x);
	}

	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		((PreparedStatement)reference).setAsciiStream(parameterIndex, x, length);
	}

	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		((PreparedStatement)reference).setAsciiStream(parameterIndex, x, length)	;
	}

	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		((PreparedStatement)reference).setBigDecimal(parameterIndex, x);
	}

	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		((PreparedStatement)reference).setBinaryStream(parameterIndex, x);
	}

	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		((PreparedStatement)reference).setBinaryStream(parameterIndex, x, length);		
	}

	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		((PreparedStatement)reference).setBinaryStream(parameterIndex, x, length);		
	}

	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		((PreparedStatement)reference).setBlob(parameterIndex, x);		
	}

	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		((PreparedStatement)reference).setBlob(parameterIndex, inputStream);
	}

	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		((PreparedStatement)reference).setBlob(parameterIndex, inputStream, length);
	}

	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		((PreparedStatement)reference).setBoolean(parameterIndex, x);		
	}

	public void setByte(int parameterIndex, byte x) throws SQLException {
		((PreparedStatement)reference).setByte(parameterIndex, x);
	}

	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		((PreparedStatement)reference).setBytes(parameterIndex, x);
	}

	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		((PreparedStatement)reference).setCharacterStream(parameterIndex, reader);
	}

	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		((PreparedStatement)reference).setCharacterStream(parameterIndex, reader);
	}

	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		((PreparedStatement)reference).setCharacterStream(parameterIndex, reader, length);
	}

	public void setClob(int parameterIndex, Clob x) throws SQLException {
		((PreparedStatement)reference).setClob(parameterIndex, x);
	}

	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		((PreparedStatement)reference).setClob(parameterIndex, reader);
	}

	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		((PreparedStatement)reference).setClob(parameterIndex, reader, length);		
	}

	public void setDate(int parameterIndex, Date x) throws SQLException {
		((PreparedStatement)reference).setDate(parameterIndex, x);
	}

	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		((PreparedStatement)reference).setDate(parameterIndex, x, cal);
	}

	public void setDouble(int parameterIndex, double x) throws SQLException {
		((PreparedStatement)reference).setDouble(parameterIndex, x);
	}

	public void setFloat(int parameterIndex, float x) throws SQLException {
		((PreparedStatement)reference).setFloat(parameterIndex, x);
	}

	public void setInt(int parameterIndex, int x) throws SQLException {
		((PreparedStatement)reference).setInt(parameterIndex, x);
	}

	public void setLong(int parameterIndex, long x) throws SQLException {
		((PreparedStatement)reference).setLong(parameterIndex, x);
	}

	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		((PreparedStatement)reference).setNCharacterStream(parameterIndex, value);
	}

	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		((PreparedStatement)reference).setNCharacterStream(parameterIndex, value);		
	}

	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		((PreparedStatement)reference).setNClob(parameterIndex, value);		
	}

	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		((PreparedStatement)reference).setNClob(parameterIndex, reader);
	}

	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		((PreparedStatement)reference).setNClob(parameterIndex, reader, length);
	}

	public void setNString(int parameterIndex, String value) throws SQLException {
		((PreparedStatement)reference).setNString(parameterIndex, value);		
	}

	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		((PreparedStatement)reference).setNull(parameterIndex, sqlType);
	}

	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
((PreparedStatement)reference).setNull(parameterIndex, sqlType);
		
	}

	public void setObject(int parameterIndex, Object x) throws SQLException {
((PreparedStatement)reference).setObject(parameterIndex, x);
		
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
((PreparedStatement)reference).setObject(parameterIndex, x);
		
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
((PreparedStatement)reference).setObject(parameterIndex, x, targetSqlType);
		
	}

	public void setRef(int parameterIndex, Ref x) throws SQLException {
((PreparedStatement)reference).setRef(parameterIndex, x);
		
	}

	public void setRowId(int parameterIndex, RowId x) throws SQLException {
((PreparedStatement)reference).setRowId(parameterIndex, x);
		
	}

	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
((PreparedStatement)reference).setSQLXML(parameterIndex, xmlObject);
		
	}

	public void setShort(int parameterIndex, short x) throws SQLException {
((PreparedStatement)reference).setShort(parameterIndex, x);
		
	}

	public void setString(int parameterIndex, String x) throws SQLException {
((PreparedStatement)reference).setString(parameterIndex, x);
		
	}

	public void setTime(int parameterIndex, Time x) throws SQLException {
((PreparedStatement)reference).setTime(parameterIndex, x);
		
	}

	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
((PreparedStatement)reference).setTime(parameterIndex, x, cal);
		
	}

	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
((PreparedStatement)reference).setTimestamp(parameterIndex, x);
		
	}

	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
((PreparedStatement)reference).setTimestamp(parameterIndex, x, cal);
		
	}

	public void setURL(int parameterIndex, URL x) throws SQLException {
((PreparedStatement)reference).setURL(parameterIndex, x);
		
	}

	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
((PreparedStatement)reference).setUnicodeStream(parameterIndex, x, length);
		
	}

	

    // Repeat for the rest of the operations
    // that the statement interface defines.
    // ...
}


