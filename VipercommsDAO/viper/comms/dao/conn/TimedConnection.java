package viper.comms.dao.conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TimedConnection
extends BaseConnectionDecorator
implements Connection {

    private ResourceTimer timer;
    private boolean closed = false;

    /**
    Constructs a TimedConnection object.
    */
    public TimedConnection(Connection reference) {
        super(reference);

        // Initialize the resource timer that monitors
        // periods of inactivity longer than 10 minutes
        // (600000 milliseconds).
        timer = new ResourceTimer(600000);

        // Register a ConnectionCloser (see the inner class
        // below) as a listener to be notified when this
        // connection remains inactive longer than this
        // period.
        timer.addResourceTimerListener(
            new ConnectionCloser());

        // Start the timer.
        timer.start();
    }

    /**
    ConnectionCloser is a custom ResourceTimerListener
    implementation that closes this connection when it
    remains inactive beyond the configured inactivity
    threshold.
    */
    private class ConnectionCloser
    implements ResourceTimerListener {

        public void resourceInactive(
            ResourceTimerEvent event) {
            try {
                close();
            }
            catch(SQLException ignore) {
            }
        }
    }

    /**
    Closes the connection.
    */
    public void close()
    throws SQLException
    {
        // There is a chance that the resource timer
        // already closed the connection, so take the extra
        // precaution to delegate the close operation only
        // once.
        if (!closed) {
            closed = true;

            // Stop the resource timer, since it is no
            // longer needed.
            timer.stop();

            super.close();
        }
    }

    // Override the rest of the Connection operations to
    // indicate activity by resetting the timer.

    public void commit() throws SQLException {
        timer.reset();
        super.commit();
    }

    public PreparedStatement prepareStatement(
        String sqlStatement) throws SQLException {
        timer.reset();
        return super.prepareStatement(sqlStatement);
    }

    // Repeat for the rest of the operations
    // that the Connection interface defines.
    // ...
}

