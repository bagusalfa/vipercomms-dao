package viper.comms.dao.cache;
public interface Statistics {

    /**
    Returns a description for the statistics.
    */
    String getDescription();

    /**
    Returns the number of hits.
    */
    long getHits();

    /**
    Returns the total number of accesses.
    */
    long getAccesses();

    /**
    Returns the cache size.
    */
    long getSize();
}

