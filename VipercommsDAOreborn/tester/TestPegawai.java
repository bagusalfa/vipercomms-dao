package tester;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;


import viper.comms.dao.conn.AbstractEntityManager;
import viper.comms.dao.conn.EntityManager;
import viper.comms.dao.conn.EntityManagerFactory;
import viper.comms.dao.conn.Persistence;
import viper.comms.dao.conn.PoolType;
import viper.comms.dao.conn.SignOn;
import viper.comms.dao.exception.DataException;
import viper.comms.dao.exception.EntityNotFoundException;
import viper.comms.dao.exception.NonexistentEntityException;
import viper.comms.dao.exception.PreexistingEntityException;

public class TestPegawai {

	/**
	 * @param args
	 */
	

	  
	public static void main(String[] args) {
		
		PegawaixJpaController xpeg =new PegawaixJpaController();
//Pegawaix pegawai=new Pegawaix(555,"BETA");
//pegawai.setNama("Mr.Beta");
//pegawai.setLahir(new Date());
//try {
//	xpeg.create(pegawai);
//} catch (PreexistingEntityException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (Exception e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
		PegawaixPK id=new PegawaixPK(555, "BETA");
		try {
			Pegawaix peg=xpeg.findPegawaix(id);
			System.out.println(peg.getNama());
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		PegawaiController pc=new PegawaiController();
//		try {
//			pc.getAnu();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			pc.getPegawai(10);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//try {
//	Pegawai pegawai=pc.findPegawai(1000);
//	System.out.println(pegawai.getNama());
//} catch (DataFormatException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (EntityNotFoundException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}

//		Pegawai pegawai=new Pegawai();
//		pegawai.setId(14);
//		pegawai.setNama("kemambang");
//		pegawai.setLahir(new Date());
//		try {
//			pc.edit(pegawai);
//		} catch (DataFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
//		for (int i = 400; i < 450; i++) {
//
//			pegawai.setId(i);
//			pegawai.setNama("paijonyono");
//pegawai.setLahir(new Date());
//			try {
//				pc.create(pegawai);
////			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//			} catch (DataFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (DataException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
//catch (DataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
//			System.out.println(pc.getPegawaiCount());
		

//		try {
//		for (int i = 10; i < 100; i++) {
//			
//		Pegawai pegawai=new Pegawai();
//		pegawai.setId(i);
//		pegawai.setNama("Djito"  + String.valueOf(i));
//
//		pc.create(pegawai);
//		}
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DataFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		List<Pegawai> list = null;
//		
//			try {
//				list = pc.findPegawaiEntities();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (DataFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//		
//		for (Pegawai pegawai : list) {
//			System.out.println(pegawai.getId());
//			System.out.println(pegawai.getNama());
//		}
		
		
//		EntityManagerFactory emf=Persistence.createEntityManagerFactory();
//		
//		EntityManager em=emf.createEntityManager();
//		
//		
//		try {
//			em.getTransaction().begin();
//		} catch (SQLException e3) {
//			// TODO Auto-generated catch block
//			e3.printStackTrace();
//		}
//		Pegawai peg=new Pegawai();
//		peg.setId(166161);
//		peg.setNama("anu");
//		
//		try {
//			em.persist(peg);
//		} catch (DataFormatException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//		try {
//			em.getTransaction().commit();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	
		
		
		
		// TODO Auto-generated method stub
//		SignOn signOn=new SignOn();
//		signOn=new SignOn();
//		signOn.setURL("jdbc:firebirdsql:localhost/3050:c:/testdao.FDB");
//		try {
//			signOn.setConnectionPool(PoolType.FIREBIRD);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		signOn.setUserName("sysdba");
//		signOn.setPassWord("masterkey");

//		Pegawai deb = new Pegawai();
//		try {
//
//			deb.InitConn(deb,signOn);
//		} catch (DataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		deb.setId(1);
//		deb.setNama("alva");
//		deb.setLahir(new Date());
//		try {
//			deb.insert(deb);
//		} catch (DataFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Pegawai nganu=new Pegawai();
//		try {
//			nganu.InitConn(nganu, signOn);
//		} catch (DataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			nganu.load(nganu);
//		} catch (DataFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(nganu.getLahir());
//		
//		nganu.setWhereInUpdate(nganu, true);
//		nganu.setLahir(new Date());
//		try {
//			nganu.update(nganu);
//		} catch (DataFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
