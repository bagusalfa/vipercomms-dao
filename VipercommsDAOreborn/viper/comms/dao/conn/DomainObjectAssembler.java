package viper.comms.dao.conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import viper.comms.dao.exception.DomainObjectException;

public class DomainObjectAssembler {

    private Connection connection;
    private String tableName;
    private SelectionFactory selectionFactory;
    private DomainObjectFactory domainObjectFactory;
    private UpdateFactory updateFactory;

    /**
    Constructs a new DomainObjectAssembler object.
    This assigns all the factory implementations.
    */
    public DomainObjectAssembler(
        Connection connection,
        String tableName,
        SelectionFactory selectionFactory,
        DomainObjectFactory domainObjectFactory,
        UpdateFactory updateFactory) {

        this.connection = connection;
        this.tableName = tableName;
        this.selectionFactory = selectionFactory;
        this.domainObjectFactory = domainObjectFactory;
        this.updateFactory = updateFactory;
    }

    /**
    Reads all domain objects that correspond to
    the specified identity object.  This returns
    an empty list if there are none.
    */
    public List read(Object identityObject)
        throws DomainObjectException {

        try {
            List domainObjects = new LinkedList();

            // Issue the query for rows that match the
            // specified identity object.
            Statement statement
                = connection.createStatement();
            ResultSet resultSet
                = issueQuery(statement, identityObject);

            // Iterate through the result set and create
            // a new domain object for each row.
            while (resultSet.next()) {

                // Get the row data and build it into a Map
                // suitable for the domain object factory.
                Map rowData = new HashMap();
                ResultSetMetaData rsmd
                    = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for(int i = 1; i <= columnCount; ++i) {
                    rowData.put(rsmd.getColumnName(i),
                                resultSet.getObject(i));
                }

                // Use the domain object factory to create
                // and populate a new domain object based
                // on the row data.
                Object domainObject
                    = domainObjectFactory
                    .newDomainObject(rowData);
                domainObjects.add(domainObject);
            }

            // Close the database resources.
            resultSet.close();
            statement.close();

            return domainObjects;
        }
        catch(SQLException e) {
            throw new DomainObjectException(e);
        }
    }

    /**
    Writes a domain object that corresponds to
    the specified identity object.
    */
    public void write(Object identityObject,
                      Object domainObject)
                      throws DomainObjectException {
        try {

            // Query the table to see if any existing rows
            // match the specified identity object.  This
            // fact helps to determine whether an insert or
            // update operation is in order.
            Statement statement
                = connection.createStatement();
            ResultSet resultSet
                = issueQuery(statement, identityObject);
            boolean rowExists = resultSet.next();
            resultSet.close();

            // If a row exists, then issue an update
            // operation.  Otherwise, issue an insert
            // operation.
            if (rowExists) {
                issueUpdate(statement,
                            identityObject,
                            domainObject);
            }
            else {
                issueInsert(statement,
                            domainObject);
            }

            // Close the database resource.
            statement.close();
        }
        catch(SQLException e) {
            throw new DomainObjectException(e);
        }
    }

    /**
    Deletes any domain objects that correspond to
    the specified identity object.
    */
    public void delete(Object identityObject)
        throws DomainObjectException {

        try {
            Statement statement
                = connection.createStatement();
            issueDelete(statement, identityObject);

            // Close the database resource.
            statement.close();
        }
        catch(SQLException e) {
            throw new DomainObjectException(e);
        }
    }
    /**
    Queries the table for rows that match the
    specified identity object.  It is the caller's
    responsibility to close the returned result set.
    */
    private ResultSet issueQuery(Statement statement,
                                 Object identityObject)
                                 throws SQLException {

        // Use the selection factory to generate the
        // selection.
        String selection
            = selectionFactory
            .newSelection(identityObject);

        // Generate the query operation.
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ");
        buffer.append(tableName);
        if (selection.length() > 0) {
            buffer.append(" WHERE ");
            buffer.append(selection);
        }

        // Issue the query operation.
        return statement.executeQuery(buffer.toString());
    }

    /**
    Updates the rows that match the specified
    identity object using the attributes from
    the specified domain object.
    */
    private void issueUpdate(Statement statement,
                             Object identityObject,
                             Object domainObject)
                             throws SQLException {

        // Use the selection factory to generate the
        // selection.
        String selection
            = selectionFactory
            .newSelection(identityObject);

        // Use the update factory to generate the
        // update.
        Map update = updateFactory.newUpdate(domainObject);

        // Generate the update operation.
        StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE ");
        buffer.append(tableName);
        buffer.append(" SET ");
        for(Iterator i = update.entrySet().iterator();
            i.hasNext(); ) {

            Map.Entry mapEntry = (Map.Entry)i.next();
            String columnName = (String)mapEntry.getKey();
            buffer.append(mapEntry.getKey());
            buffer.append(" = ");
            Object columnValue = mapEntry.getValue();
            if (!(columnValue instanceof Number))
                buffer.append("'");
            buffer.append(columnValue);
            if (!(columnValue instanceof Number))
                buffer.append("'");
            if (i.hasNext())
                buffer.append(", ");
        }

        if (selection.length() > 0) {
            buffer.append(" WHERE ");
            buffer.append(selection);
        }

        // Issue the update operation.
        statement.executeUpdate(buffer.toString());
    }

    /**
    Inserts a new row using the attributes of the
    specified domain object.
    */
    private void issueInsert(Statement statement,
                             Object domainObject)
                             throws SQLException {

        // Use the update factory to generate the
        // update.
        Map update = updateFactory.newUpdate(domainObject);

        // Generate the insert operation.
        StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO ");
        buffer.append(tableName);
        buffer.append(" (");
        Set columnNames = update.keySet();
        for(Iterator i=columnNames.iterator();i.hasNext();) {
            buffer.append(i.next()); // The column name.
            if (i.hasNext())
                buffer.append(",");
        }
        buffer.append(") VALUES (");
        for(Iterator i=columnNames.iterator();i.hasNext();) {
            Object columnValue = update.get(i.next());
            if (!(columnValue instanceof Number))
                buffer.append("'");
            buffer.append(columnValue);
            if (!(columnValue instanceof Number))
                buffer.append("'");
            if (i.hasNext())
                buffer.append(", ");
        }
        buffer.append(")");

        // Issue the insert operation.
        statement.executeUpdate(buffer.toString());
    }

    /**
    Deletes the rows that match the specified
    identity object.
    */
    private void issueDelete(Statement statement,
                             Object identityObject)
                             throws SQLException {

        // Use the selection factory to generate the
        // selection.
        String selection
            = selectionFactory
            .newSelection(identityObject);

        // Generate the delete operation.
        StringBuffer buffer = new StringBuffer();
        buffer.append("DELETE FROM ");
        buffer.append(tableName);
        if (selection.length() > 0) {
            buffer.append(" WHERE ");
            buffer.append(selection);
        }

        // Issue the delete operation.
        statement.executeUpdate(buffer.toString());
    }
}

