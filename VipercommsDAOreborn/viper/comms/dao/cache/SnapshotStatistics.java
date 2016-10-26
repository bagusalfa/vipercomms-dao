package viper.comms.dao.cache;
public class SnapshotStatistics
implements Statistics {

    private String description;
    private long hits;
    private long accesses;
    private long size;

    public SnapshotStatistics(
        String description,
        long hits,
        long accesses,
        long size) {

        this.description = description;
        this.hits = hits;
        this.accesses = accesses;
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public long getHits() {
        return hits;
    }

    public long getAccesses() {
        return accesses;
    }

    public long getSize() {
        return size;
    }
}

