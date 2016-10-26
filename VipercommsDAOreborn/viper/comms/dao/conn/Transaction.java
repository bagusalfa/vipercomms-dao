package viper.comms.dao.conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface Transaction {
public void begin() throws SQLException;
public void commit() throws SQLException ;
public void rollback() throws SQLException;
public void setConnection(Connection conn) throws SQLException;
public void prepareStatement(String sqlStatement) throws SQLException;
public void executeUpdate() throws SQLException;
public PreparedStatement getPrepStatement();
public Connection getConnection();
public Statement createStatement() throws SQLException;
public Statement getStatement() throws SQLException;

}