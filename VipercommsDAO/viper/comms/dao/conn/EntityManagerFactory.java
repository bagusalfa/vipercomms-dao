package viper.comms.dao.conn;

import java.sql.SQLException;
import java.util.Map;

import viper.comms.dao.exception.DataException;

public class EntityManagerFactory {

	EntityManager em=null;
	Map thisMap=null;
	String idEntity=null;
	
	protected EntityManagerFactory(String string, Map map) {
	thisMap=map;
	idEntity=string;
	}

	protected EntityManagerFactory() {
		// TODO Auto-generated constructor stub
	}


	
	public EntityManager createEntityManager( ){
		if (em == null){
			if (idEntity==null){
		 em= new EntityManager();
			em.TryOpenDefaultConnection(em);
			}else{
				 em= new EntityManager(idEntity,thisMap);
				 SignOn loginObject=new SignOn();
				 loginObject.setDriverName((String) thisMap.get("vipercommsdao.jdbc.driver"));
				 loginObject.setPassWord((String) thisMap.get("vipercommsdao.jdbc.password"));
				 loginObject.setURL((String) thisMap.get("vipercommsdao.jdbc.url"));
				 loginObject.setUserName((String) thisMap.get("vipercommsdao.jdbc.user"));
				try {

					 loginObject.setConnectionPool(loginObject.getDriverName());
					em.InitConn(em, loginObject);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return em;
	}
}
