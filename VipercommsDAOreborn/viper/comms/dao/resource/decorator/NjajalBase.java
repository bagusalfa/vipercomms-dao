package viper.comms.dao.resource.decorator;

import java.sql.*;

public class NjajalBase {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
//		 Get the appropriate physical connection.
		Connection connection = DriverManager.getConnection(" ");

//		 Attach leak detection to the connection.
		connection = new LeakDetectionConnectionDecorator(connection, System.out);

//		 The rest of the application works the same with
//		 or without the additional functionality.
//		 ...


	}

}
