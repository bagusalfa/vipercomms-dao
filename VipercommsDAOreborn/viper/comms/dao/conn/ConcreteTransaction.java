package viper.comms.dao.conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.TRANSACTION_MODE;

public class ConcreteTransaction implements Transaction {
	Connection myConn;
	PreparedStatement prepStatement;
	Statement statement;
	
	public void begin() throws SQLException {
		// TODO Auto-generated method stub
		myConn.setAutoCommit(false);
		myConn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED );
	}

	public void commit() throws SQLException {
		myConn.commit();
		myConn.setAutoCommit(true);

	}

	public void rollback() throws SQLException {
		myConn.rollback();
		myConn.setAutoCommit(true);
	}

	public void setConnection(Connection conn) {
		myConn=conn;		
	}
	public void prepareStatement(String sqlStatement) throws SQLException{
		prepStatement=myConn.prepareStatement(sqlStatement);
	}
	public void executeUpdate() throws SQLException{
		prepStatement.executeUpdate();
	}

	public PreparedStatement getPrepStatement() {
		return prepStatement;
	}

	public Connection getConnection() {
		
		return myConn;
	}

	public Statement createStatement() throws SQLException {
		statement=myConn.createStatement();
		return statement;
	}

	public Statement getStatement() throws SQLException {	
		return statement;
	}

}
