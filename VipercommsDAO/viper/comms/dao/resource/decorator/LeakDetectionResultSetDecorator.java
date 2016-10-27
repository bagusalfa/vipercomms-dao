package viper.comms.dao.resource.decorator;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeakDetectionResultSetDecorator 
extends BaseResultSetDecorator 
		 {
	 private static int openResultSets = 0;
	 private static int nextId = 0;
	 private int id;
	 private PrintStream out;

	public LeakDetectionResultSetDecorator(ResultSet reference) {
		super(reference);
		 
	}

	public LeakDetectionResultSetDecorator(ResultSet reference, PrintStream out) {
		
//		 Initialize the reference and print stream.
        super(reference);
        this.out = out;

        // Assign this statement a unique identifier.
        id = nextId++;

        // Report that a statement has been created and
        // dump the current stack trace.
        out.println("ResultSets " + id + " was created. "
            + "Open resultsets = " + (++openResultSets)
            + ".");
        (new Throwable()).printStackTrace(out);
	}

	/**
	 * @param args
	 */
	
	public void close() throws SQLException {

        // Delegate the close operation.
        super.close();

        // Report that a connection has been closed.
        out.println("ResultSet " + id + " was closed. "
            + "Open ResultSets = " + (--openResultSets)
            + ".");
    }
	
	

}
