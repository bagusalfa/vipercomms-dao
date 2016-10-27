package viper.comms.dao.conn;

import java.sql.*;
import java.util.*;


public class StatementCachingConnection
extends BaseConnectionDecorator
implements Connection {

    // The contents of the statement cache.
    // This maps statement keys to lists of
    // available statements.
    private Map statementCache = new HashMap();

    public StatementCachingConnection(Connection delegate) {
        super(delegate);
    }

    /**
    Get an appropriate statement from the cache, if
    available.
    **/
    public PreparedStatement prepareStatement(String sql,
        int resultSetType,
        int resultSetConcurrency)
        throws SQLException {

        // Form the statement key.  For simplicity, this
        // example stores it as a string that incorporates
        // each of the prepareStatement parameters.
        StringBuffer buffer = new StringBuffer();
        buffer.append(sql);
        buffer.append(":");
        buffer.append(resultSetType);
        buffer.append(":");
        buffer.append(resultSetConcurrency);
        Object statementKey = buffer.toString();

        // Check to see if there is a matching
        // statement list in the cache.  If there is
        // not, create a new one.
        List statementList;
        synchronized(statementCache) {
            statementList
                = (List)statementCache.get(statementKey);
            if (statementList == null) {
                statementList = new LinkedList();
                statementCache.put(statementKey,
                                   statementList);
            }
        }

        // Check to see if there is a statement in
        // the statement list.  If there is at least
        // one, remove it from the list.
        // Otherwise, prepare a new one.
        PreparedStatement statement;
        synchronized(statementList) {
            if (statementList.size() > 0) {
                statement = (PreparedStatement)
                    statementList.remove(0);
            }
            else {
                // Delegate the prepareStatement call.
                statement = super.prepareStatement(sql,
                    resultSetType, resultSetConcurrency);
            }
        }

        // Decorate the statement to be a CachedStatement.
        // This will cause it to be returned to the cache
        // when the client closes it.
        statement = new CachedStatement(this,statementKey, statement);

        return statement;
    }

    // ... similar for other overloaded prepareStatement
    //     and prepareCall methods.


    // CachedStatment.close calls this.
    void putStatement(Object statementKey,
                      PreparedStatement statement) {

        // Resolve the statement list for the specified
        // statement key.
        List statementList;
        synchronized(statementCache) {
            statementList
                = (List)statementCache.get(statementKey);
        }

        // Add the statement to the list.
        synchronized(statementList) {
            statementList.add(statement);
        }
    }
}

