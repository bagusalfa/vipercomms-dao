package viper.comms.dao.conn;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import viper.comms.dao.exception.DomainObjectException;



public class PagingIterator {

    private ResultSet resultSet;
    private ResultSetMetaData rsmd;
    private int columnCount;
    private DomainObjectFactory domainObjectFactory;

    /**
    Constructs a PagingIterator object.
    */
    public PagingIterator(
        ResultSet resultSet,
        DomainObjectFactory domainObjectFactory)
        throws DomainObjectException {

        this.resultSet = resultSet;
        this.domainObjectFactory = domainObjectFactory;

        try {
            this.rsmd = (ResultSetMetaData) resultSet.getMetaData();
            this.columnCount = rsmd.getColumnCount();
        }
        catch(SQLException e) {
            throw new DomainObjectException(e);
        }
    }

    /**
    Fetches the next page of domain objects.  The
    list is empty when there is no more data.
    */
    public List nextPage(int pageSize)
        throws DomainObjectException {

        try {
            List domainObjects = new LinkedList();

            // Set the result set's fetch size to match
            // the page size.  Many JDBC drivers use this
            // as a hint to optimize blocked fetches.
            resultSet.setFetchSize(pageSize);

            for(int i = 0;
                i < pageSize && resultSet.next();
                ++i) {

                // Get the next row of data.
                Map rowData = new HashMap();
                for(int j = 1; j <= columnCount; ++j) {
                    rowData.put(rsmd.getColumnName(j),
                                resultSet.getObject(j));
                }

                // Use the domain object factory to initialize
                // a new domain object based on the row data.
                Object domainObject
                    = domainObjectFactory
                    .newDomainObject(rowData);

                domainObjects.add(domainObject);
            }

            return domainObjects;
        }
        catch(SQLException e) {
            throw new DomainObjectException(e);
        }
    }

    /**
    Closes the result set that this paging
    iterator manages.
    */
    public void close() throws DomainObjectException {
        try {
            resultSet.close();
        }
        catch(SQLException e) {
            throw new DomainObjectException(e);
        }
    }
}

