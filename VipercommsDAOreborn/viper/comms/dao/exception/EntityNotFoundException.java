package viper.comms.dao.exception;

import java.sql.SQLException;

public class EntityNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public EntityNotFoundException(String message) {
	        super(message);
	    }
	
	public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

	public EntityNotFoundException(SQLException e) {
		super(e);
	}
}