package viper.comms.dao.cache;

import java.util.Collection;

public class CollectionStatistics
implements CurrentStatistics {

    private long hits = 0;
    private long accesses = 0;
    private String description;
    private Collection collection;

    public CollectionStatistics(
        String description,
        Collection collection) {

        this.description = description;
        this.collection = collection;
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
        return collection.size();
    }

    public Statistics getSnapshot() {
        return new SnapshotStatistics(
            description,
            hits,
            accesses,
            collection.size());
    }

    public void markHit() {
        ++hits;
        ++accesses;
    }

    public void markMiss() {
        ++accesses;    
    }
}

