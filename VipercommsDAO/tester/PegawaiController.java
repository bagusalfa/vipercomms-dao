package tester;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import sun.java2d.Disposer;
import viper.comms.dao.anno.ColumnMeth;
import viper.comms.dao.conn.AbstractEntityManager;
import viper.comms.dao.conn.ConcreteDataAccessor;
import viper.comms.dao.conn.EntityManager;
import viper.comms.dao.conn.EntityManagerFactory;
import viper.comms.dao.conn.Persistence;
import viper.comms.dao.conn.Query;
import viper.comms.dao.conn.Row;
import viper.comms.dao.conn.Transaction;
import viper.comms.dao.conn.TransactionContext;
import viper.comms.dao.exception.DataException;
import viper.comms.dao.exception.EntityNotFoundException;
import viper.comms.dao.exception.NonexistentEntityException;

public class PegawaiController  {
	EntityManager em = null;

	public PegawaiController() {
		// Map map =new HashMap();
		// map.put("vipercommsdao.jdbc.user", "sysdba");
		// map.put("vipercommsdao.jdbc.password", "masterkey");
		// map.put( "vipercommsdao.jdbc.url",
		// "jdbc:firebirdsql:localhost/3050:c:/testdao.FDB");
		// map.put("vipercommsdao.jdbc.driver",
		// "org.firebirdsql.jdbc.FBDriver");
		// emf = Persistence.createEntityManagerFactory("DAOJPU",map);
		emf = Persistence.createEntityManagerFactory();

	}

	private EntityManagerFactory emf = null;

	private EntityManager getEntityManager() {

		em = emf.createEntityManager();
		em.setDataAccessor();
		em.setObjectTemplate(Pegawai.class);	
		return em;
	}

	TransactionContext context=null;
	
	public void create(Pegawai pegawai) throws SQLException, DataFormatException,DataException {

		em = getEntityManager();

		em.getTransaction().begin();
		 //context=em.getContext();
		
		em.persist(pegawai);
		em.getTransaction().commit();

	}

	public List getPegawai(int i) throws SQLException  {

//		em = getEntityManager();
//		em.getTransaction().begin();
//        context=em.getContext();
//	    Transaction transaction=context.getTransaction();
//	  
//	   
//	    transaction.prepareStatement( "select * from Pegawai where id=? ");
//	    PreparedStatement pstmt = transaction.getPrepStatement();
//	    pstmt.setInt(1, i);
//	   
//	    ResultSet rs = pstmt.executeQuery();
//	      while (rs.next()) {
//	        System.out.print(rs.getInt(1) + " \t| ");	 
//	        System.out.print(rs.getString(2) + " \t| ");	  
//	      }
//	      
//		em.commit();
		
		em = getEntityManager();
		em.getTransaction().begin();
        
		Query q =  em.createQuery(
				"select * from Pegawai where id=?");
		q.setParameter(1, i);
		em.getTransaction().commit();
		return q.getResultList();
		
	}
	public List getPegawaiSemua() throws SQLException  {

		em = getEntityManager();
	
        
		Query q =  em.createQuery(
				"select * from Pegawai ");
		
			
		return  q.getResultList();
	}
	public int getPegawaiCount() throws SQLException  {

		em = getEntityManager();
        em.setObjectTemplate(Pegawai.class);
        
		Query q =  em.createQuery(
				"select count(*) as theCount from Pegawai ");
		
			
		return (Integer) q.getSingleResult();
	}
	
	public void getAnu() throws SQLException  {

		em = getEntityManager();
        em.setObjectTemplate(Pegawai.class);
        
	
			
		List anu=em.createQuery("select sum(angpk),sum(angbg),kredit.norek,nama,kredit.tanggal as tglkredit,tgljtempo,kredit.pokok as krpok,angsur.bunga,marketing,angpk,angbg,angsur.tanggal as agstgl,"+
"angsur.pokok as agspok,denda,selisih,saldo "+
"from kredit inner join angsur on kredit.norek=angsur.norek "+

"group by kredit.norek,nama,kredit.tanggal ,tgljtempo,kredit.pokok ,angsur.bunga,marketing,angpk,angbg,angsur.tanggal ,"+
"angsur.pokok ,denda,selisih,saldo").getResultList();
		for (Object object : anu) {
			
		}
	}
	
	public Pegawai findPegawai(int id) throws DataFormatException, EntityNotFoundException  {
		em = getEntityManager();

		return (Pegawai) em.find(id,Pegawai.class);

	}

	public void destroy(int id) throws NonexistentEntityException, DataFormatException, DataException  {
		em = getEntityManager();
		try {
			em.getReference(Pegawai.class, id);
		} catch (EntityNotFoundException e) {
			throw new NonexistentEntityException("The pegawai with id " + id + " no longer exists.", e);
		}
		em.remove(id,Pegawai.class);
		

	}

	public void edit(Pegawai pegawai) throws DataFormatException, DataException  {
		em = getEntityManager();

		em.merge(pegawai);

	}


	public List<Pegawai> findPegawaiEntities() throws DataFormatException, SQLException {
		 return findPegawaiEntities(true, -1, -1);

	}

	public List<Pegawai> findPegawaiEntities(int maxResults, int firstResult)
			throws SQLException, DataFormatException {
		return findPegawaiEntities(false, maxResults, firstResult);
	}

	private List<Pegawai> findPegawaiEntities(boolean all, int maxResults,
			int firstResult) throws SQLException, DataFormatException {

		em = getEntityManager();
		   Query q = em.createQuery( "select * from Pegawai");
           if (!all) {
               q.setMaxResults(maxResults);
               q.setFirstResult(firstResult);
           }
           
           return q.getResultList();
	

	}
}
