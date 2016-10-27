package viper.comms.dao.cache;
public interface CurrentStatistics
extends Statistics {

    /**
    Returns a snapshot of the statistics.
    */
    Statistics getSnapshot();
}
