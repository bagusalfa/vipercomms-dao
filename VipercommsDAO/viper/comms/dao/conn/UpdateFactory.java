package viper.comms.dao.conn;

import java.util.Map;

public interface UpdateFactory {

    /**
    Returns a Map that contains the column names
    and values to be updated based on the
    attributes of the specified domain object.
    */
    Map newUpdate(Object domainObject);

}
