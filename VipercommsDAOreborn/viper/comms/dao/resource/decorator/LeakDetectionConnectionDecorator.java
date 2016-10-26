package viper.comms.dao.resource.decorator;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeakDetectionConnectionDecorator
extends BaseConnectionDecorator {

    private static int openConnections = 0;
    private static int nextId = 0;
    private int id;
    private PrintStream out;

    /**
    Constructs a LeakDetectionConnectionDecorator object.

    @param reference The reference Connection implementation.
    @param out The print stream to which this object writes
               debug messages.
    */
    public LeakDetectionConnectionDecorator(
        Connection reference, PrintStream out) {

        // Initialize the reference and print stream.
        super(reference);
        this.out = out;

        // Assign this connection a unique identifier.
        id = nextId++;

        // Report that a connection has been created and
        // dump the current stack trace.
        out.println("Connection " + id + " was created. "
            + "Open connections = " + (++openConnections)
            + ".");
        (new Throwable()).printStackTrace(out);
    }

    public void close() throws SQLException {

        // Delegate the close operation.
        super.close();

        // Report that a connection has been closed.
        out.println("Connection " + id + " was closed. "
            + "Open connections = " + (--openConnections)
            + ".");
    }

    public Statement createStatement() throws SQLException {

        // Wrap the concrete Statement in a
        // LeakDetectionStatementDecorator object.
        Statement statement = super.createStatement();
        return new LeakDetectionStatementDecorator(
            statement, out);
    }

    // There is no need to override methods to which we are
    // not attaching additional behavior.  To complete this
    // example, we need to override the other methods that
    // create Statements to decorate all such objects
    // appropriately.
}



