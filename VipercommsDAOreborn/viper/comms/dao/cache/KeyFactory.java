package viper.comms.dao.cache;
public interface KeyFactory {

    /**
    Returns a specific key based on a domain object.
    */
    Key newSpecificKey(Object domainObject);
}