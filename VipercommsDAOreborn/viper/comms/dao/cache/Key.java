package viper.comms.dao.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Key implements IKey{

    private Map contents = new TreeMap();

    public void set(
        String columnName,
        Object columnValue) {

        contents.put(columnName, columnValue);
    }
    
    /**
    Indicates whether this key is a partial
    specification of another.
    */
    public boolean isPartialOf(Key specificKey) {
        for(Iterator i = contents.keySet().iterator();
            i.hasNext(); ) {

            String attributeName = (String)i.next();

            Object partialKeyValue
                = this.get(attributeName);
            Object specificKeyValue
                = specificKey.get(attributeName);

            if (partialKeyValue != null
                && !partialKeyValue.equals(specificKeyValue))
                return false;
        }

        return true;
    }


    public Iterator columnNames() {
        return contents.keySet().iterator();
    }

    public Object get(String columnName) {
        return contents.get(columnName);
    }

    public int hashCode() {
        return contents.hashCode();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Key))
            return false;

        Key otherKey = (Key)other;
        return contents.equals(otherKey.contents);
    }

    public String toStringContent() {
        return contents.toString();
    }
    /**
    Returns a JDO query filter.  CacheAccessor uses this
    when it issues database read operations.
    */
    public String toString() {

        StringBuffer buffer = new StringBuffer();
        boolean isFirstAttribute = true;
        for(Iterator i = contents.entrySet().iterator();
            i.hasNext(); ) {

            if (isFirstAttribute)
                isFirstAttribute = false;
            else
                buffer.append(" & ");

            Map.Entry entry = (Map.Entry)i.next();
            String attributeName = (String)entry.getKey();
            Object attributeValue = entry.getValue();

            buffer.append(attributeName);
            buffer.append(" == ");
            if (!(attributeValue instanceof Number))
                buffer.append("'");
            buffer.append(attributeValue);
            if (!(attributeValue instanceof Number))
                buffer.append("'");
        }

        return buffer.toString();
    }

	public boolean generalizes(IKey otherKey) {
		// TODO Auto-generated method stub
		return false;
	}
}


