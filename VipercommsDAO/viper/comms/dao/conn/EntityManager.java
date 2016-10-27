package viper.comms.dao.conn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.zip.DataFormatException;

import tester.Pegawai;
import tester.Pegawaix;
import tester.PegawaixPK;
import viper.comms.dao.exception.DataException;

public class EntityManager extends AbstractEntityManager  {
 ConcreteDataAccessor da=null;
//private TransactionContext context;
 
	public EntityManager(String idEntity, Map thisMap) {
	// TODO Auto-generated constructor stub
}

	public EntityManager() {
		// TODO Auto-generated constructor stub
	}

	public EntityManager getTransaction(){
		da=getDataAccessor(this);
		return this;
	}
	
	public void begin() throws SQLException{
		da.beginTransaction();
		
	}
	
	public TransactionContext getContext() {
		return da.getContext();
	}
	public void persist(Object object) throws DataFormatException, DataException{
		this.setDataAccessor();
		insert( this,object);
	}
	
	public void commit() throws SQLException{
		da.commit();
	}
	public void rollback() throws SQLException{
		da.rollback();
	}

	public void close() {
		da=null;
		
	}

	public void getReference(Class<Pegawaix> templateObject, PegawaixPK id) {
		// TODO Auto-generated method stub
		
	}





	
}
