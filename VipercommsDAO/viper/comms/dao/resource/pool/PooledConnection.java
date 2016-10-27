package viper.comms.dao.resource.pool;

import java.sql.Connection;
import java.sql.SQLException;

import viper.comms.dao.resource.decorator.BaseConnectionDecorator;

class PooledConnection
extends BaseConnectionDecorator
implements Connection {

    private ConnectionPool connectionPool;
    private Object connectionKey;
    private Connection reference;
    private boolean closed = false;

    public PooledConnection(ConnectionPool connectionPool,
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
