package viper.comms.dao.cache;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CacheSearchSequence {

    private CacheAccessor cacheAccessor;
    private Map cache;
    private DirectedGraph sequenceKeys = new DirectedGraph();
    private boolean done = false;
    private IKey lastKey = null;

    public CacheSearchSequence(
        CacheAccessor cacheAccessor,
        Map cache) {

        this.cacheAccessor = cacheAccessor;
        this.cache = cache;
    }

    /**
    Reads data from the cache.

    @param key  The key.
    @return     The data.
    **/
    public List read(IKey key) {

        if (done)
            throw new IllegalStateException("done");

        // Add this key to the list of those
        // in this sequence.
        sequenceKeys.add(key);
        lastKey = key;

        // Get the values using the cache accessor.
        List values = cacheAccessor.read((Key) key);

        // If values are found, then implicitly end
        // this sequence.
        if(values != null)
            end(values);

        return values;
    }

    /**
    Marks the sequence as done.  Call this if no values
    are found after trying every key in the sequence.
    */
    public void end() {

        // Mark the end of the sequence with an
        // empty list to indicate that no values were
        // found.
        if (!done)
            end(Collections.EMPTY_LIST);
    }

    /**
    Marks the sequence as done.
    */
    private void end(List values) {
        if (lastKey != null) {

            // Place a shortcut entry in the cache for
            // each key that the last key in the sequence
            // generalizes.
            Set specifications
                = sequenceKeys.getSpecifications(lastKey);
            for(Iterator i = specifications.iterator();
                i.hasNext(); ) {
                Object key = i.next();
                cache.put(key, values);
            }

        }

        done = true;
    }
}

