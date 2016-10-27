package viper.comms.dao.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DirectedGraph {
    private Map contents = new HashMap();

    /**
    Adds a key to the directed graph.
    */
    public void add(IKey key) {

        // Resolve the collection of specifications that
        // applies to the key.  Create it if one does not
        // already exist.
        Set specifications = (Set)contents.get(key);
        if (specifications == null) {
            specifications = new HashSet();
            contents.put(key, specifications);
        }

        // Iterate through each of the keys in the graph.
        for(Iterator i = contents.entrySet().iterator();
            i.hasNext(); ) {

            Map.Entry entry = (Map.Entry)i.next();
            IKey existingKey = (IKey)entry.getKey();
            Set existingSpecifications
                = (Set)entry.getValue();

            // Update the specification relationships for
            // the keys.
            if (existingKey.equals(key))
                ;
            else {
                if (existingKey.generalizes(key))
                    existingSpecifications.add(key);
                if (key.generalizes(existingKey))
                    specifications.add(existingKey);
            }
        }
    }

    /**
    Returns the collection of keys that are specifications
    of the given key.
    */
    public Set getSpecifications(IKey key) {
        Set specifications = (Set)contents.get(key);
        if (specifications != null)
            return specifications;
        else
            return Collections.EMPTY_SET;
    }
}