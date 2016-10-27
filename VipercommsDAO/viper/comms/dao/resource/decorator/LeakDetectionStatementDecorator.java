package viper.comms.dao.resource.decorator;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeakDetectionStatementDecorator
extends BaseStatementDecorator {

    private static int openStatements = 0;
    private static int nextId = 0;
    private int id;
    private PrintStream out;

    /**
    Constructs a LeakDetectionStatementDecorator object.

    @param reference The reference Statement implementation.
    @param out The print stream to which this object writes
               debug messages.
    */
    public LeakDetectionStatementDecorator(
        Statement reference, PrintStream out) {

        // Initialize the reference and print stream.
        super(reference);
        this.out = out;

        // Assign this statement a unique identifier.
        id = nextId++;

        // Report that a statement has been created and
        // dump the current stack trace.
        out.println("Statement " + id + " was created. "
            + "Open statements = " + (++openStatements)
            + ".");
        (new Throwable()).printStackTrace(out);
    }

    public void close() throws SQLException {

        // Delegate the close operation.
        super.close();

        // Report that a statement has been closed.
        out.println("Statement " + id + " was closed. "
            + "Open statements = " + (--openStatements)
            + ".");
    }

    public ResultSet executeQuery(String sql)
    throws SQLException {

        // Wrap the concrete ResultSet in a
        // LeakDetectionResultSetDecorator object.
        // (This class is not included in this example,
        // but is similar to this one.)
        ResultSet resultSet = super.executeQuery(sql);
        return new LeakDetectionResultSetDecorator(
            resultSet, out);
    }

    // There is no need to override methods to which we are
    // not attaching additional behavior.  To complete this
    // example, we need to override the other methods that
    // create ResultSets to decorate all such objects
    // appropriately.
}
