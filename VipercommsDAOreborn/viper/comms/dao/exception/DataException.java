package viper.comms.dao.exception;

import java.sql.SQLException;

public class DataException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataException(String message) {
        super(message);
    }
	public DataException(String message, Throwable cause) {
        super(message, cause);
    }

	public DataException(SQLException e) {
		super(e);
	}
}


