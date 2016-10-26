package viper.comms.dao.conn;

import java.util.Map;

public interface DomainObjectFactory {

    /**
    Creates and returns a new domain object
    that corresponds to the contents of a
    single row from database query results.
    */
    Object newDomainObject(Map rowData);

    /**
    Returns an array of column names that
    this factory expects to see in the
    row data that gets passed to
    newDomainObject.
    */
    String[] getColumnNames();

}

