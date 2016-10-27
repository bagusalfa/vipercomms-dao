package viper.comms.dao.conn;

import java.util.List;

public interface Query {
	public Object setParameter(int paramIndex, Object theValue);
	
	public List getResultList();
	
	public void setMaxResults(int maxResult);
	
	public void setFirstResult(int firstResult);
	public Object getSingleResult();
}
