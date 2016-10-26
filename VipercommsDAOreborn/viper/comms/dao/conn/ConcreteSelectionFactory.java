package viper.comms.dao.conn;
public class ConcreteSelectionFactory
implements SelectionFactory {

    public String newSelection(String fieldName,String containObject,FilterOperation oper )
    {
    	
        String vin = containObject;
        StringBuffer buffer = new StringBuffer();
        buffer.append(SearchInjection.Scan((fieldName)));
        buffer.append(" = '");
        buffer.append(SearchInjection.Scan(vin));
        buffer.append("'");
        return buffer.toString();
    }

	public String newSelection(Object identityObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

