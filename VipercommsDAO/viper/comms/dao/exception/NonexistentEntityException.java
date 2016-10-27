package viper.comms.dao.exception;

import java.sql.SQLException;

public class NonexistentEntityException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public NonexistentEntityException(String message) {
	        super(message);
	    }
	
	public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }

	public NonexistentEntityException(SQLException e) {
		super(e);
	}
}
