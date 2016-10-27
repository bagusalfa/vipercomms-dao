package viper.comms.dao.resource.decorator;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class BaseResultSetDecorator implements ResultSet {

	private ResultSet reference;
	
	public BaseResultSetDecorator(ResultSet reference) {		
		this.reference = reference;
	}	
	
	
	public boolean next() throws SQLException {
		
		return reference.next();
	}

	public void close() throws SQLException {
		
		reference.close();
	}

	public boolean wasNull() throws SQLException {
		
		return reference.wasNull();
	}

	public String getString(int arg0) throws SQLException {
		
		return reference.getString(arg0);
	}

	public boolean getBoolean(int arg0) throws SQLException {
		
		return reference.getBoolean(arg0);
	}

	public byte getByte(int arg0) throws SQLException {
		
		return reference.getByte(arg0);
	}

	public short getShort(int arg0) throws SQLException {
		
		return reference.getShort(arg0);
	}

	public int getInt(int arg0) throws SQLException {
		
		return reference.getInt(arg0);
	}

	public long getLong(int arg0) throws SQLException {
		
		return reference.getLong(arg0);
	}

	public float getFloat(int arg0) throws SQLException {
		
		return reference.getFloat(arg0);
	}

	public double getDouble(int arg0) throws SQLException {
		
		return reference.getDouble(arg0);
	}

	
	public BigDecimal getBigDecimal(int arg0, int arg1) throws SQLException {
		
		return reference.getBigDecimal(arg0);
	}

	public byte[] getBytes(int arg0) throws SQLException {
		
		return reference.getBytes(arg0) ;
	}

	
	public Date getDate(int arg0) throws SQLException {
		
		return reference.getDate(arg0);
	}

	public Time getTime(int arg0) throws SQLException {
		
		return reference.getTime(arg0);
	}

	public Timestamp getTimestamp(int arg0) throws SQLException {
		
		return reference.getTimestamp(arg0);
	}

	public InputStream getAsciiStream(int arg0) throws SQLException {
		
		return reference.getAsciiStream(arg0);
	}

	public InputStream getUnicodeStream(int arg0) throws SQLException {
		
		return reference.getUnicodeStream(arg0);
	}

	public InputStream getBinaryStream(int arg0) throws SQLException {
		
		return reference.getBinaryStream(arg0);
	}

	public String getString(String arg0) throws SQLException {
		
		return reference.getString(arg0);
	}

	public boolean getBoolean(String arg0) throws SQLException {
		
		return reference.getBoolean(arg0);
	}

	public byte getByte(String arg0) throws SQLException {
		
		return reference.getByte(arg0);
	}

	public short getShort(String arg0) throws SQLException {
		
		return reference.getShort(arg0);
	}

	public int getInt(String arg0) throws SQLException {
		
		return reference.getInt(arg0);
	}

	public long getLong(String arg0) throws SQLException {
		
		return reference.getLong(arg0);
	}

	public float getFloat(String arg0) throws SQLException {
		
		return reference.getFloat(arg0);
	}

	public double getDouble(String arg0) throws SQLException {
		
		return reference.getDouble(arg0);
	}

	public BigDecimal getBigDecimal(String arg0, int arg1) throws SQLException {
		
		return (BigDecimal)reference.getBigDecimal(arg0,arg1);
	}

	public byte[] getBytes(String arg0) throws SQLException {
		
		return reference.getBytes(arg0);
	}

	public Date getDate(String arg0) throws SQLException {
		
		return reference.getDate(arg0);
	}

	public Time getTime(String arg0) throws SQLException {
		
		return reference.getTime(arg0);
	}

	public Timestamp getTimestamp(String arg0) throws SQLException {
		
		return reference.getTimestamp(arg0);
	}

	public InputStream getAsciiStream(String arg0) throws SQLException {
		
		return reference.getAsciiStream(arg0);
	}

	public InputStream getUnicodeStream(String arg0) throws SQLException {
		
		return reference.getUnicodeStream(arg0);
	}

	public InputStream getBinaryStream(String arg0) throws SQLException {
		
		return reference.getBinaryStream(arg0);
	}

	public SQLWarning getWarnings() throws SQLException {
		
		return reference.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		
		reference.clearWarnings();
	}

	public String getCursorName() throws SQLException {
		
		return reference.getCursorName();
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		
		return reference.getMetaData();
	}

	public Object getObject(int arg0) throws SQLException {
		
		return reference.getObject(arg0);
	}

	public Object getObject(String arg0) throws SQLException {
		
		return reference.getObject(arg0);
	}

	public int findColumn(String arg0) throws SQLException {
		
		return reference.findColumn(arg0);
	}

	public Reader getCharacterStream(int arg0) throws SQLException {
		
		return reference.getCharacterStream(arg0);
	}

	public Reader getCharacterStream(String arg0) throws SQLException {
		
		return reference.getCharacterStream(arg0);
	}

	public BigDecimal getBigDecimal(int arg0) throws SQLException {
		
		return reference.getBigDecimal(arg0);
	}

	public BigDecimal getBigDecimal(String arg0) throws SQLException {
		
		return reference.getBigDecimal(arg0);
	}

	public boolean isBeforeFirst() throws SQLException {
		
		return reference.isBeforeFirst();
	}

	public boolean isAfterLast() throws SQLException {
		
		return reference.isAfterLast();
	}

	public boolean isFirst() throws SQLException {
		
		return reference.isFirst();
	}

	public boolean isLast() throws SQLException {
		
		return reference.isLast() ;
	}

	public void beforeFirst() throws SQLException {
		
		reference.beforeFirst();
	}

	public void afterLast() throws SQLException {
		
		reference.afterLast();
	}

	public boolean first() throws SQLException {
		
		return reference.first();
	}

	public boolean last() throws SQLException {
		
		return reference.last();
	}

	public int getRow() throws SQLException {
		
		return reference.getRow();
	}

	public boolean absolute(int arg0) throws SQLException {
		
		return reference.absolute(arg0);
	}

	public boolean relative(int arg0) throws SQLException {
		
		return reference.relative(arg0);
	}

	public boolean previous() throws SQLException {
		
		return reference.previous();
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

	public int getType() throws SQLException {
		
		return reference.getType();
	}

	public int getConcurrency() throws SQLException {
		
		return reference.getConcurrency();
	}

	public boolean rowUpdated() throws SQLException {
		
		return reference.rowUpdated();
	}

	public boolean rowInserted() throws SQLException {
		
		return reference.rowInserted();
	}

	public boolean rowDeleted() throws SQLException {
		
		return reference.rowDeleted();
	}

	public void updateNull(int arg0) throws SQLException {
		
		reference.updateNull(arg0);
	}

	public void updateBoolean(int arg0, boolean arg1) throws SQLException {
		
		reference.updateBoolean(arg0,arg1);
	}

	public void updateByte(int arg0, byte arg1) throws SQLException {
		
		reference.updateByte(arg0,arg1);
	}

	public void updateShort(int arg0, short arg1) throws SQLException {
		
		reference.updateShort(arg0,arg1);
	}

	public void updateInt(int arg0, int arg1) throws SQLException {
		
		reference.updateInt(arg0,arg1);
	}

	public void updateLong(int arg0, long arg1) throws SQLException {
		
		reference.updateLong(arg0,arg1);
	}

	public void updateFloat(int arg0, float arg1) throws SQLException {
		
		reference.updateFloat(arg0,arg1);
	}

	public void updateDouble(int arg0, double arg1) throws SQLException {
		
		reference.updateDouble(arg0,arg1);
	}

	public void updateBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
		
		reference.updateBigDecimal(arg0,arg1);
	}

	public void updateString(int arg0, String arg1) throws SQLException {
		
		reference.updateString(arg0,arg1);
	}

	public void updateBytes(int arg0, byte[] arg1) throws SQLException {
		
		reference.updateBytes(arg0,arg1);
	}

	public void updateDate(int arg0, Date arg1) throws SQLException {
		
		reference.updateDate(arg0,arg1);
	}

	public void updateTime(int arg0, Time arg1) throws SQLException {
		
		reference.updateTime(arg0,arg1);
	}

	public void updateTimestamp(int arg0, Timestamp arg1) throws SQLException {
		
		reference.updateTimestamp(arg0,arg1);
	}

	public void updateAsciiStream(int arg0, InputStream arg1, int arg2)
			throws SQLException {
		
		reference.updateAsciiStream(arg0,arg1,arg2);
	}

	public void updateBinaryStream(int arg0, InputStream arg1, int arg2)
			throws SQLException {
		
		reference.updateBinaryStream(arg0,arg1,arg2);
	}

	public void updateCharacterStream(int arg0, Reader arg1, int arg2)
			throws SQLException {
		
		reference.updateCharacterStream(arg0,arg1,arg2);
	}

	public void updateObject(int arg0, Object arg1, int arg2)
			throws SQLException {
		
		reference.updateObject(arg0,arg1,arg2);
	}

	public void updateObject(int arg0, Object arg1) throws SQLException {
		
		reference.updateObject(arg0,arg1);
	}

	public void updateNull(String arg0) throws SQLException {
		
		reference.updateNull(arg0);
	}

	public void updateBoolean(String arg0, boolean arg1) throws SQLException {
		
		reference.updateBoolean(arg0,arg1);
	}

	public void updateByte(String arg0, byte arg1) throws SQLException {
		
		reference.updateByte(arg0,arg1);
	}

	public void updateShort(String arg0, short arg1) throws SQLException {
		
		reference.updateShort(arg0,arg1);
	}

	public void updateInt(String arg0, int arg1) throws SQLException {
		
		reference.updateInt(arg0,arg1);
	}

	public void updateLong(String arg0, long arg1) throws SQLException {
		
		reference.updateLong(arg0,arg1);
	}

	public void updateFloat(String arg0, float arg1) throws SQLException {
		
		reference.updateFloat(arg0,arg1);
	}

	public void updateDouble(String arg0, double arg1) throws SQLException {
		
		reference.updateDouble(arg0,arg1);
	}

	public void updateBigDecimal(String arg0, BigDecimal arg1)
			throws SQLException {
		
		reference.updateBigDecimal(arg0,arg1);
	}

	public void updateString(String arg0, String arg1) throws SQLException {
		
		reference.updateString(arg0,arg1);
	}

	public void updateBytes(String arg0, byte[] arg1) throws SQLException {
		
		reference.updateBytes(arg0,arg1);
	}

	public void updateDate(String arg0, Date arg1) throws SQLException {
		
		reference.updateDate(arg0,arg1);
	}

	public void updateTime(String arg0, Time arg1) throws SQLException {
		
		reference.updateTime(arg0,arg1);
	}

	public void updateTimestamp(String arg0, Timestamp arg1)
			throws SQLException {
		
		reference.updateTimestamp(arg0,arg1);
	}

	public void updateAsciiStream(String arg0, InputStream arg1, int arg2)
			throws SQLException {
		
		reference.updateAsciiStream(arg0,arg1,arg2);
	}

	public void updateBinaryStream(String arg0, InputStream arg1, int arg2)
			throws SQLException {
		
		reference.updateBinaryStream(arg0,arg1,arg2);
	}

	public void updateCharacterStream(String arg0, Reader arg1, int arg2)
			throws SQLException {
		
		reference.updateCharacterStream(arg0,arg1,arg2);
	}

	public void updateObject(String arg0, Object arg1, int arg2)
			throws SQLException {
		
		reference.updateObject(arg0,arg1,arg2);
	}

	public void updateObject(String arg0, Object arg1) throws SQLException {
		
		reference.updateObject(arg0,arg1);
	}

	public void insertRow() throws SQLException {
		
		reference.insertRow();
	}

	public void updateRow() throws SQLException {
		
		reference.updateRow();
	}

	public void deleteRow() throws SQLException {
		
		reference.deleteRow();
	}

	public void refreshRow() throws SQLException {
		
		reference.refreshRow();
	}

	public void cancelRowUpdates() throws SQLException {
		
		reference.cancelRowUpdates();
	}

	public void moveToInsertRow() throws SQLException {
		
		reference.moveToInsertRow();
	}

	public void moveToCurrentRow() throws SQLException {
		
		reference.moveToCurrentRow();
	}

	public Statement getStatement() throws SQLException {
		
		return reference.getStatement();
	}

	public Object getObject(int arg0, Map<String, Class<?>> arg1)
			throws SQLException {
		
		return reference.getObject(arg0,arg1);
	}

	public Ref getRef(int arg0) throws SQLException {
		
		return reference.getRef(arg0);
	}

	public Blob getBlob(int arg0) throws SQLException {
		
		return reference.getBlob(arg0);
	}

	public Clob getClob(int arg0) throws SQLException {
		
		return reference.getClob(arg0);
	}

	public Array getArray(int arg0) throws SQLException {
		
		return reference.getArray(arg0);
	}

	public Object getObject(String arg0, Map<String, Class<?>> arg1)
			throws SQLException {
		
		return reference.getObject(arg0,arg1);
	}

	public Ref getRef(String arg0) throws SQLException {
		
		return reference.getRef(arg0);
	}

	public Blob getBlob(String arg0) throws SQLException {
		
		return reference.getBlob(arg0);
	}

	public Clob getClob(String arg0) throws SQLException {
		
		return reference.getClob(arg0);
	}

	public Array getArray(String arg0) throws SQLException {
		
		return reference.getArray(arg0);
	}

	public Date getDate(int arg0, Calendar arg1) throws SQLException {
		
		return reference.getDate(arg0,arg1);
	}

	public Date getDate(String arg0, Calendar arg1) throws SQLException {
		
		return reference.getDate(arg0,arg1);
	}

	public Time getTime(int arg0, Calendar arg1) throws SQLException {
		
		return reference.getTime(arg0,arg1);
	}

	public Time getTime(String arg0, Calendar arg1) throws SQLException {
		
		return reference.getTime(arg0,arg1);
	}

	public Timestamp getTimestamp(int arg0, Calendar arg1) throws SQLException {
		
		return reference.getTimestamp(arg0,arg1);
	}

	public Timestamp getTimestamp(String arg0, Calendar arg1)
			throws SQLException {
		
		return reference.getTimestamp(arg0,arg1);
	}

	public URL getURL(int arg0) throws SQLException {
		
		return reference.getURL(arg0);
	}

	public URL getURL(String arg0) throws SQLException {
		
		return reference.getURL(arg0);
	}

	public void updateRef(int arg0, Ref arg1) throws SQLException {
		
		reference.updateRef(arg0,arg1);
	}

	public void updateRef(String arg0, Ref arg1) throws SQLException {
		
		reference.updateRef(arg0,arg1);
	}

	public void updateBlob(int arg0, Blob arg1) throws SQLException {
		
		reference.updateBlob(arg0,arg1);
	}

	public void updateBlob(String arg0, Blob arg1) throws SQLException {
		
		reference.updateBlob(arg0,arg1);
	}

	public void updateClob(int arg0, Clob arg1) throws SQLException {
		
		reference.updateClob(arg0,arg1);
	}

	public void updateClob(String arg0, Clob arg1) throws SQLException {
		
		reference.updateClob(arg0,arg1);
	}

	public void updateArray(int arg0, Array arg1) throws SQLException {
		
		reference.updateArray(arg0,arg1);
	}

	public void updateArray(String arg0, Array arg1) throws SQLException {
		
		reference.updateArray(arg0,arg1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}


	public RowId getRowId(int columnIndex) throws SQLException {
		
		return reference.getRowId(columnIndex);
	}


	public RowId getRowId(String columnLabel) throws SQLException {
		
		return reference.getRowId(columnLabel);
	}


	public void updateRowId(int columnIndex, RowId x) throws SQLException {
		
		reference.updateRowId(columnIndex,x);	
	}


	public void updateRowId(String columnLabel, RowId x) throws SQLException {
		
		reference.updateRowId(columnLabel,x);
	}


	public int getHoldability() throws SQLException {
		
		return reference.getHoldability();
	}


	public boolean isClosed() throws SQLException {
		
		return reference.isClosed();
	}


	public void updateNString(int columnIndex, String nString) throws SQLException {
		
		reference.updateNString(columnIndex,nString);		
	}


	public void updateNString(String columnLabel, String nString) throws SQLException {
		
		reference.updateNString(columnLabel,nString);	
	}


	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
		
		
	}


	public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
		
		
	}


	public NClob getNClob(int columnIndex) throws SQLException {
		
		return null;
	}


	public NClob getNClob(String columnLabel) throws SQLException {
		
		return null;
	}


	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		
		return null;
	}


	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		
		return null;
	}


	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
		
		
	}


	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
		
		
	}


	public String getNString(int columnIndex) throws SQLException {
		
		return null;
	}


	public String getNString(String columnLabel) throws SQLException {
		
		return null;
	}


	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		
		return null;
	}


	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		
		return null;
	}


	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		
		
	}


	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		
		
	}


	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
		
		
	}


	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
		
		
	}


	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		
		
	}


	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
		
		
	}


	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
		
		
	}


	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		
		
	}


	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
		
		
	}


	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
		
		
	}


	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
		
		
	}


	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
		
		
	}


	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
		
		
	}


	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
		
		
	}


	public <T> T unwrap(Class<T> iface) throws SQLException {
		
		return null;
	}


	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		
		return false;
	}


	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateClob(int columnIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateClob(String columnLabel, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateNClob(int columnIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void updateNClob(String columnLabel, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}



}
