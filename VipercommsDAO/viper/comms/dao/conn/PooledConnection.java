package viper.comms.dao.conn;

import java.sql.Connection;
import java.sql.SQLException;


class PooledConnection
extends BaseConnectionDecorator
implements Connection {

    private IConnectionPool connectionPool;
    private Object connectionKey;
    private Connection reference;
    private boolean closed = false;

    public PooledConnection(IConnectionPool connectionPool,
                            Object connectionKey,
                            Connection reference)  {
        super(reference);
        this.connectionPool = connectionPool;
        this.connectionKey = connectionKey;
        this.reference = reference;
    }

    

	/**
    Do not really close the reference connection.  Instead,
    just return it to the pool.
    **/
    public void close() throws SQLException {
        if (!closed) {
            closed = true;
            connectionPool.putConnection(
                connectionKey, reference);
        }
    }
}
