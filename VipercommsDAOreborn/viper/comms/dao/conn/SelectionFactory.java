package viper.comms.dao.conn;
public interface SelectionFactory {

    /**
    Returns a SQL WHERE clause that corresponds
    to the identity object.
    */
    String newSelection(String fieldName,String containObject,FilterOperation oper);

	String newSelection(Object identityObject);
}

