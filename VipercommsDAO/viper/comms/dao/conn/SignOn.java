package viper.comms.dao.conn;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class SignOn implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String userName;
private String passWord;
private String URL;
private String driverName;
private IConnectionPool connectionPool;
private PoolType poolType=PoolType.FIREBIRD;
private boolean IsUsingPooledConnection=true;

public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getURL() {
	return URL;
}
public void setURL(String url) {
	URL = url;
}
public IConnectionPool getConnectionPool() {
	return connectionPool;
}
public void setConnectionPool(String dbaseDriver) throws SQLException{
	connectionPool=new ConnectionPoolHandlerLateBinding(dbaseDriver);
	connectionPool.getConnection(URL, userName, passWord);
}

public void setConnectionPool(PoolType poolType) throws SQLException {
	switch (poolType) {
	case FIREBIRD:
	{
		connectionPool=new ConnectionPoolHandler(new org.firebirdsql.jdbc.FBDriver());
	}
		break;
	case MYSQL:
	{
//		connectionPool=new ConnectionPoolMySql();
		connectionPool=new ConnectionPoolHandler(new com.mysql.jdbc.Driver());

	}
	case DERBY:
	{
		connectionPool=new ConnectionPoolHandler(new org.apache.derby.jdbc.EmbeddedDriver());
	}
		break;
	case POSTGRESQL:
	{
		connectionPool=new ConnectionPoolHandler( new org.postgresql.Driver());
	}
		break;
	case SQLSERVER:
	{
		connectionPool=new ConnectionPoolHandler(new net.sourceforge.jtds.jdbc.Driver());
	}
		break;
	case GENERALODBC:
	{
		connectionPool=new ConnectionPoolHandler(new sun.jdbc.odbc.JdbcOdbcDriver());
	}
		break;
	case LATEBINDING:
	{
		connectionPool=new ConnectionPoolHandlerLateBinding(driverName);
	}
		break;
	default:
		break;
	}
	connectionPool.setUsingPooledConnection(IsUsingPooledConnection);
	this.connectionPool = connectionPool;

}
public String getDriverName() {
	return driverName;
}
public void setDriverName(String driverName) {
	this.driverName = driverName;
}
public boolean isUsingPooledConnection() {
	return IsUsingPooledConnection;
}
public void setUsingPooledConnection(boolean isUsingPooledConnection) {
	IsUsingPooledConnection = isUsingPooledConnection;
}


}
