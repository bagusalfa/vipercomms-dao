package viper.comms.dao.conn;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import tester.Pegawai;
import viper.comms.dao.anno.Column;
import viper.comms.dao.anno.ColumnMeth;
import viper.comms.dao.anno.Id;
import viper.comms.dao.anno.IdMeth;
import viper.comms.dao.conn.ConcreteDataAccessor;
import viper.comms.dao.conn.IsiBaris;
import viper.comms.dao.conn.Row;
import viper.comms.dao.conn.FilterOperation;
import viper.comms.dao.conn.SignOn;
import viper.comms.dao.exception.DataException;
import viper.comms.dao.exception.EntityNotFoundException;
import viper.comms.util.Vutil;

public abstract class AbstractEntityManager implements Query {
	ConcreteDataAccessor dataAccessor = null;
	Row baris = null;
	Row updateRow = null;
	FilterOperation operMode = FilterOperation.UNKNOWN;
	boolean setModeUpdate = false;
	List<Row> resultQuery = null;
	List<Row> resetRow = null;
	Iterator i = null;
	String username = null;
	String password = null;
	String url = null;

	String driverName;
	PoolType poolType;
	boolean IsUsingNoResultException = true;

	public boolean isIsUsingNoResultException() {
		return IsUsingNoResultException;
	}

	public void setIsUsingNoResultException(boolean isUsingNoResultException) {
		IsUsingNoResultException = isUsingNoResultException;
	}

	/**
	 * Melakukan persiapan array protokol
	 * 
	 * @param NamaField
	 * @param Isi
	 */
	public static void prepArray(AbstractEntityManager self, String NamaField,
			Object Isi) {
		if ((NamaField != null) && (Isi != null)) {
			synchronized (self) {

				IsiBaris objBaris = new IsiBaris();
				objBaris.addParam(self.operMode);
				objBaris.addBaris(NamaField, Isi);

				if (!self.setModeUpdate) {
					if (self.baris == null) {
						self.baris = new Row();
					}
					self.baris.addColumn(NamaField, objBaris);
				} else {
					if (self.updateRow == null) {
						self.updateRow = new Row();
					}
					self.updateRow.addColumn(NamaField, objBaris);
				}
			}
		}

	}

	private void prepareUpdate(Object objectTemplate) {
		scanField(this, objectTemplate);
		for (Iterator iterator = this.baris.columns(); iterator.hasNext();) {
			String kunci = (String) iterator.next();

			IsiBaris objBaris = (IsiBaris) this.baris.getColumnValue(kunci);

			if (this.updateRow == null) {
				this.updateRow = new Row();
			}
			this.updateRow.addColumn(kunci, objBaris);

		}
		this.baris = null;
	}

	public Object find(Object id, Object templateObject)
			throws DataFormatException, EntityNotFoundException {
		Object theObject;
		try {
			theObject = ((Class<? extends Object>) templateObject)
					.newInstance();

			lebokkeIDneKeObject(id, theObject);

			scanAllField(this, theObject);
			this.dataAccessor.IsUsingNoResultException = IsUsingNoResultException;
			List countryRows = this.dataAccessor.readMOExclusiveParam(
					AbstractEntityManager.getIDclass(theObject), null,
					this.baris, null, FilterOperation.AND_TYPE);
			for (Iterator i = countryRows.iterator(); i.hasNext();) {
				Row countryRow = (Row) i.next();
				this.LoadFromRow(countryRow, theObject);
			}
			return theObject;

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// }
	}

	public boolean remove(Object id, Object templateObject)
			throws DataFormatException, DataException {

		try {
			Object theObject = ((Class<? extends Object>) templateObject)
					.newInstance();

			lebokkeIDneKeObject(id, theObject);
			scanAllField(this, theObject);
			this.dataAccessor.IsUsingNoResultException = IsUsingNoResultException;
			return this.dataAccessor.deleteInExclusiveParam(
					AbstractEntityManager.getIDclass(theObject), this.baris,
					FilterOperation.AND_TYPE);

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void lebokkeIDneKeObject(Object id, Object templateObject) {

		Map methMap = new HashMap();

//		for (Method m : templateObject.getClass().getMethods()) {
//
//			if (m.isAnnotationPresent(IdMeth.class)) {
//
//				String namaField = m.getName().replace("get", "").toUpperCase();
//				String set_nameOfField = "set" + m.getName().replace("get", "");
//				map.put(set_nameOfField, id);
//
//			}
//
//		}
		
		for (Field fd : templateObject.getClass().getDeclaredFields()) {
			if (fd.isAnnotationPresent(Id.class)) {
				String namaField = fd.getName().toUpperCase();
				methMap.put("SET" + namaField, id);
			}
			if (fd.isAnnotationPresent(EmbeddedId.class)) {
				String namaField = fd.getName().toUpperCase();
				methMap.put("SET" + namaField, id);
			}

		}
		
		for (Method m : templateObject.getClass().getMethods()) {
			if (methMap.containsKey(m.getName().toUpperCase())) {
				try {
					m.invoke(templateObject, methMap.get(m.getName().toUpperCase()));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean insert(AbstractEntityManager self,
			Object templateObject) throws DataFormatException, DataException {
		synchronized (self) {
			scanAllField(self, templateObject);
			List<Row> untukInsert = new LinkedList<Row>();
			;
			untukInsert.add(self.baris);
			self.dataAccessor.IsUsingNoResultException = self.IsUsingNoResultException;
			return self.dataAccessor.insert(AbstractEntityManager
					.getIDclass(templateObject), untukInsert);
		}
	}

	private static void scanField(AbstractEntityManager self,
			Object objectTemplate) {
		try {

			Map methMap = new HashMap();

			for (Method m : objectTemplate.getClass().getMethods()) {

//				 if (m.getName()=="setNama") {
//				 try {
//				 m.invoke(self,"kelpoyanto");
//				
//				 //System.out.println(anu);
//				 } catch (IllegalArgumentException e) {
//				 // TODO Auto-generated catch block
//				 e.printStackTrace();
//				 } catch (IllegalAccessException e) {
//				 // TODO Auto-generated catch block
//				 e.printStackTrace();
//				 } catch (InvocationTargetException e) {
//				 // TODO Auto-generated catch block
//				 e.printStackTrace();
//				 }
//					  					
//				 }

				if (m.getName().startsWith("get")) {
				Object methValue = m.invoke(objectTemplate);
				methMap.put(m.getName().toUpperCase(), methValue);
				}
				// if (m.isAnnotationPresent(ColumnMeth.class)) {
				// // System.out.println(m.getName());
				// String namaField = m.getName().replace("get", "")
				// .toUpperCase();
				//
				// try {
				// Object Isi = m.invoke(objectTemplate);
				// methMap.put(m.getName().toUpperCase(), Isi);
				// prepArray(self, namaField, Isi);
				// // System.out.println(anu);
				// } catch (IllegalArgumentException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (IllegalAccessException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (InvocationTargetException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				//
				// }
				// System.out.println(m.getName());

				

			}
			for (Field fd : objectTemplate.getClass().getDeclaredFields()) {
				if (fd.isAnnotationPresent(Column.class)) {

					String namaField = fd.getName().toUpperCase();
					try {
						
					Object isi = methMap.get("GET" + namaField);
					prepArray(self, namaField, isi);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void scanFieldID(AbstractEntityManager self,
			Object objectTemplate) {
		try {
			Map methMap = new HashMap();
			for (Method m : objectTemplate.getClass().getMethods()) {
//				if (m.isAnnotationPresent(IdMeth.class)) {
//					// System.out.println(m.getName());
//					String namaField = m.getName().replace("get", "")
//							.toUpperCase();
//
//					try {
//						Object Isi = m.invoke(objectTemplate);
//						prepArray(self, namaField, Isi);
//						// System.out.println(anu);
//					} catch (IllegalArgumentException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IllegalAccessException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (InvocationTargetException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//				}
				if (m.getName().startsWith("get")) {
				Object methValue = m.invoke(objectTemplate);
				methMap.put(m.getName().toUpperCase(), methValue);
				}
				// System.out.println(m.getName());
			}
			
			for (Field fd : objectTemplate.getClass().getDeclaredFields()) {
				if (fd.isAnnotationPresent(EmbeddedId.class)){
					String namaField = fd.getName().toUpperCase();
					try {
						
					Object isi = methMap.get("GET" + namaField);
					Map embMap = new HashMap();
					for (Method m : isi.getClass().getMethods()) {
						if (m.getName().startsWith("get")) {
							Object methValue = m.invoke(isi);
							embMap.put(m.getName().toUpperCase(), methValue);
							}
					}
					for (Field field : isi.getClass().getDeclaredFields()) {
						String namaFieldPK = field.getName().toUpperCase();
						Object isiObj=embMap.get("GET"+namaFieldPK.toUpperCase());
						prepArray(self, namaFieldPK, isiObj);
					}
				
					
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				if (fd.isAnnotationPresent(Id.class)) {

					String namaField = fd.getName().toUpperCase();
					try {
						
					Object isi = methMap.get("GET" + namaField);
					prepArray(self, namaField, isi);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void scanAllField(AbstractEntityManager self,
			Object objectTemplate) {
		scanField(self, objectTemplate);
		scanFieldID(self, objectTemplate);
	}

	public boolean merge(Object objectTemplate) throws DataFormatException,
			DataException {
		prepareUpdate(objectTemplate);
		scanFieldID(this, objectTemplate);
		this.dataAccessor.IsUsingNoResultException = IsUsingNoResultException;
		return this.dataAccessor.updateInExclusiveParam(AbstractEntityManager
				.getIDclass(objectTemplate), this.baris, this.updateRow,
				FilterOperation.AND_TYPE);

	}

	// public static String getIDclass(AbstractEntityManager self) {
	public static String getIDclass(Object self) {
		synchronized (self) {

			return self.getClass().getSimpleName();
		}
	}

	public static void ResetRow(AbstractEntityManager self) {
		synchronized (self) {

			self.resultQuery = self.resetRow;
		}
	}

	public static boolean HasNext(AbstractEntityManager self) {
		synchronized (self) {

			Iterator i = self.resultQuery.iterator();
			if (i.hasNext()) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static ConcreteDataAccessor getDataAccessor(
			AbstractEntityManager self) {
		synchronized (self) {

			return self.dataAccessor;
		}
	}

	// public boolean deleteInExclusiveParam() throws DataFormatException{
	// return dataAccessor.deleteInExclusiveParam(getIDclass() , baris,
	// FilterOperation.AND_TYPE);
	// }
	// public boolean updateInExclusiveParam() throws DataFormatException{
	// return dataAccessor.updateInExclusiveParam(getIDclass() , baris,
	// updateRow, FilterOperation.AND_TYPE);
	// }
	public static boolean isSetModeUpdate(AbstractEntityManager self) {
		synchronized (self) {

			return self.setModeUpdate;
		}
	}

	public static void setWhereInUpdate(AbstractEntityManager self,
			boolean setModeUpdate) {
		synchronized (self) {

			self.setModeUpdate = setModeUpdate;
		}
	}

	public static boolean BeginTransaction(AbstractEntityManager self)
			throws SQLException {
		synchronized (self) {

			return self.dataAccessor.beginTransaction();
		}
	}

	public static boolean Commit(AbstractEntityManager self) {
		synchronized (self) {

			try {
				self.dataAccessor.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	public static boolean Rollback(AbstractEntityManager self) {
		synchronized (self) {

			try {
				self.dataAccessor.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	public static List<Row> ExecQuery(AbstractEntityManager self,
			String querySQL) {
		synchronized (self) {

			return self.dataAccessor.execQuery(querySQL);
		}
	}

	public static ResultSet ExecQueryResultSet(AbstractEntityManager self,
			String querySQL) {
		synchronized (self) {

			return self.dataAccessor.execQueryRS(querySQL);
		}
	}

	private ResultSet rSet = null;
	private List<Row> resultList = null;
	private Object objectForTemplate = null;

	// private String objectPackage=null;

	public void setObjectTemplate(Object objectPackage) {
		try {
			objectForTemplate = ((Class<? extends Object>) objectPackage)
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// private String getObjectFromQuerySQL(String querySQL){
	// String str=querySQL.toLowerCase();
	// Scanner scan = new Scanner(str).useDelimiter("from");
	// scan.next();
	// str=scan.next();
	// Scanner scanDeep =new Scanner(str).useDelimiter(" ");
	// str=scanDeep.next();
	// System.out.println(str);
	// return str;
	// }

	private boolean IsUsingParams(String querySQL) {
		if (querySQL.contains(":")) {
			return true;
		}
		if (querySQL.contains("?")) {
			return true;
		}

		return false;
	}

	boolean UsingTransaction = false;
	PreparedStatement preparedStatement = null;

	public AbstractEntityManager createQuery(String querySQL)
			throws SQLException {

		// rSet = this.dataAccessor.execQueryRS(querySQL);
		if (!IsUsingParams(querySQL)) {
			resultList = this.dataAccessor.execQuery(querySQL);
		} else {
			// transaction.prepareStatement(
			// "select * from Pegawai where id=? ");

			if (this.dataAccessor.getTransaction() == null) {
				UsingTransaction = false;
			} else {
				UsingTransaction = true;
			}
			if (UsingTransaction) {
				this.dataAccessor.getTransaction().prepareStatement(querySQL);
			} else {
				preparedStatement = this.dataAccessor.getConnection()
						.prepareStatement(querySQL);
			}
		}

		return this;

	}

	public static boolean NonExecQuery(AbstractEntityManager self,
			String querySQL) {
		synchronized (self) {

			return self.dataAccessor.execNonQuery(querySQL);
		}
	}

	public static List readDynamic(AbstractEntityManager self,
			String whereClause, String orderByClause) {
		synchronized (self) {

			return self.dataAccessor.readDynamic(AbstractEntityManager
					.getIDclass(self), whereClause, orderByClause);
		}
	}

	public static ResultSet readDynamicResultSet(AbstractEntityManager self,
			String whereClause, String orderByClause) {
		synchronized (self) {

			return self.dataAccessor.readDynamicRS(AbstractEntityManager
					.getIDclass(self), whereClause, orderByClause);
		}
	}

	// public static boolean Next(AbstractEntityManager self){
	// synchronized (self) {
	//
	// if (self.i.hasNext()){
	// Row countryRow = (Row)self.i.next();
	// self.LoadFromRow(countryRow);
	// }
	// return true;
	// }
	// }

	public static List loadRows(AbstractEntityManager self, Object object)
			throws DataFormatException {
		// synchronized (self) {

		List<Row> daftarObject = new ArrayList<Row>();
		List countryRows = self.dataAccessor.read(AbstractEntityManager
				.getIDclass(object), null, self.baris, null,
				FilterOperation.AND_TYPE);
		boolean firstItem = true;
		for (Iterator i = countryRows.iterator(); i.hasNext();) {
			Row countryRow = (Row) i.next();
			if (firstItem) {
				self.LoadFromRow(countryRow, object);
				firstItem = false;
			}
			daftarObject.add(countryRow);
		}
		self.resultQuery = daftarObject;
		self.resetRow = daftarObject;
		self.i = self.resultQuery.iterator();
		return daftarObject;
		// }
	}

	public List findAll(Object object) throws DataFormatException {
		// synchronized (self) {
		List hasile = new ArrayList();
		List<Row> daftarObject = new ArrayList<Row>();
		List countryRows = this.dataAccessor.read(AbstractEntityManager
				.getIDclass(object), null, this.baris, null,
				FilterOperation.AND_TYPE);
		boolean firstItem = true;
		for (Iterator i = countryRows.iterator(); i.hasNext();) {
			Row countryRow = (Row) i.next();
			try {
				Object test = object.getClass().newInstance();
				this.LoadFromRow(countryRow, test);
				daftarObject.add(countryRow);
				hasile.add(test);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		this.resultQuery = daftarObject;
		this.resetRow = daftarObject;
		this.i = this.resultQuery.iterator();

		return hasile;

	}

	// public boolean ReadInExclusiveParam() throws DataFormatException {
	// List countryRows
	// = dataAccessor.readMOExclusiveParam(getIDclass(), null, baris, null,
	// FilterOperation.AND_TYPE);
	// for(Iterator i = countryRows.iterator(); i.hasNext(); ) {
	// Row countryRow = (Row)i.next();
	// LoadFromRow(countryRow);
	// }
	// return true;
	// }

	public boolean LoadFromRow(Row row, Object templateObject) {
		Map map = new HashMap();
        
		try {
			
			
//			for (Method m : templateObject.getClass().getMethods()) {
//
//				
//				
//				if (m.isAnnotationPresent(ColumnMeth.class)) {
//
//					String namaField = m.getName().replace("get", "")
//							.toUpperCase();
//					String set_nameOfField = "set"
//							+ m.getName().replace("get", "");
//					map.put(set_nameOfField, row.getColumnValue(namaField));
//
//				}
//			}

			
//			for (Method m : templateObject.getClass().getMethods()) {
//
//				if (m.isAnnotationPresent(IdMeth.class)) {
//
//					String namaField = m.getName().replace("get", "")
//							.toUpperCase();
//					String set_nameOfField = "set"
//							+ m.getName().replace("get", "");
//					map.put(set_nameOfField, row.getColumnValue(namaField));
//
//				}
//
//			}
		
			for (Field fd : templateObject.getClass().getDeclaredFields()){
				
				if (fd.isAnnotationPresent(EmbeddedId.class)){
					String emb =fd.getDeclaringClass().getCanonicalName();
					Object objPK=Class.forName(emb).newInstance();
					Map mapPK = new HashMap();
					for (Field fieldPK : objPK.getClass().getDeclaredFields()){
						String fieldNamePK ="set"+ToSentenceCase(fieldPK.getName());
						mapPK.put(fieldNamePK, row.getColumnValue(fieldPK.getName().toUpperCase()));
					}
					for (Method mPK : objPK.getClass().getMethods()) {
						if (mPK.getName().startsWith("set")) {
							if (map.containsKey(mPK.getName())) {
								mPK.invoke(templateObject, mapPK.get(mPK.getName()));
							}
						}
					}
								
					
				}else{

					String fieldName ="set"+ToSentenceCase(fd.getName());
					map.put(fieldName, row.getColumnValue(fd.getName().toUpperCase()));
				}
				
				
			}
			
			for (Method m : templateObject.getClass().getMethods()) {
				if (m.getName().startsWith("set")) {
					if (map.containsKey(m.getName())) {
						m.invoke(templateObject, map.get(m.getName()));
					}
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
		// TODO Auto-generated method stub

	}
	public static String ToSentenceCase(String masukan) {
		String kalimat = masukan;
		kalimat = kalimat.toLowerCase();
		kalimat = kalimat.substring(1);
		kalimat = masukan.substring(0, 1).toUpperCase() + kalimat;

		return kalimat;
	}
	public static void InitConn(AbstractEntityManager self,
			ConcreteDataAccessor dataAccessor) {
		synchronized (self) {

			self.dataAccessor = dataAccessor;
		}
	}

	public static void InitConn(AbstractEntityManager self)
			throws DataFormatException, DataException {
		synchronized (self) {

			self.dataAccessor = new ConcreteDataAccessor();
		}
	}

	public static void InitConn(AbstractEntityManager self, SignOn loginObject)
			throws DataException {
		// synchronized (self) {

		self.dataAccessor = new ConcreteDataAccessor(loginObject);
		// }
	}

	// public static void setDataAccessor(AbstractEntityManager self,
	// ConcreteDataAccessor mydataAccessor) {
	// synchronized (self) {
	//
	// self.dataAccessor = mydataAccessor;
	// }
	// }

	public void setDataAccessor() {

		this.dataAccessor = this.getDataAccessor(this);

	}

	public static void setDetailWhereOperation(AbstractEntityManager self,
			FilterOperation operationMode) {
		synchronized (self) {

			self.operMode = operationMode;
		}
	}

	public static void TryOpenDefaultConnection(AbstractEntityManager self) {

		final String PROP_FILE = "persist.properties";
		Properties p2 = new Properties();
		p2 = Vutil.loadProperties(PROP_FILE);

		self.username = p2.getProperty("vipercommsdao.jdbc.user");
		self.password = p2.getProperty("vipercommsdao.jdbc.password");
		self.url = p2.getProperty("vipercommsdao.jdbc.url");
		String fbDriver = p2.getProperty("vipercommsdao.jdbc.driver");
		if (p2.getProperty("connectionPool").equalsIgnoreCase("FIREBIRD")) {
			self.poolType = PoolType.FIREBIRD;
		}

		SignOn so = new SignOn();
		so.setUserName(self.username);
		so.setPassWord(self.password);
		so.setURL(self.url);
		so.setDriverName(self.driverName);
		try {
			// so.setConnectionPool(self.poolType);
			so.setConnectionPool(fbDriver);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			InitConn(self, so);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	int maxResultQuery;
	int firstResultQuery;

	public Object setParameter(int paramIndex, Object theValue) {

		if (UsingTransaction) {
			preparedStatement = this.dataAccessor.getTransaction()
					.getPrepStatement();
		}
		try {
			preparedStatement.setInt(paramIndex, (Integer) theValue);

			rSet = preparedStatement.executeQuery();
			ResultSetMetaData rsmd = rSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			// Create a list of result rows based on the
			// contents of the result set.
			List<Row> resultRows = new LinkedList<Row>();
			while (rSet.next()) {
				Row resultRow = new Row();
				for (int i = 1; i <= columnCount; ++i) {
					resultRow.addColumn(rsmd.getColumnName(i), rSet
							.getObject(i));
				}
				resultRows.add(resultRow);
			}
			resultList = resultRows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// em.getTransaction().begin();
		// context=getContext();
		// Transaction transaction=context.getTransaction();
		// Transaction transaction=da.getTransaction();
		//	   
		// transaction.prepareStatement( "select * from Pegawai where id=? ");
		// PreparedStatement pstmt = transaction.getPrepStatement();
		// pstmt.setInt(1, i);
		//	   
		// ResultSet rs = pstmt.executeQuery();
		// while (rs.next()) {
		// System.out.print(rs.getInt(1) + " \t| ");
		// System.out.print(rs.getString(2) + " \t| ");
		// }
		//	      
		// em.commit();
		return null;
	}

	public List getResultList() {
		try {
			Object objectToList = null;
			List theResultObjectList = new ArrayList();
			int i = 1;
			int count = 1;
			for (Row row : resultList) {
				objectToList = Class.forName(
						objectForTemplate.getClass().getName()).newInstance();
				LoadFromRow(row, objectToList);
				// maxResultQuery
				// firstResultQuery
				if (firstResultQuery > 0) {
					if (i >= firstResultQuery) {
						if (count <= maxResultQuery) {
							theResultObjectList.add(objectToList);
							count++;
						}
					}
				} else {
					theResultObjectList.add(objectToList);
				}

				i++;
			}
			return theResultObjectList;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setMaxResults(int maxResult) {
		maxResultQuery = maxResult;
	}

	public void setFirstResult(int firstResult) {
		firstResultQuery = firstResult;
	}

	public Object getSingleResult() {

		for (Row row : resultList) {
			for (Iterator iterator = row.columns(); iterator.hasNext();) {
				String columnName = (String) iterator.next();
				return row.getColumnValue(columnName);

			}

		}
		return null;

	}

	public void getReference(Object templateObject, int i)
			throws EntityNotFoundException {
		Object resultObject;
		try {
			resultObject = this.find(i, templateObject);
		} catch (DataFormatException e) {

			throw new EntityNotFoundException("Entity no longer exists.", e);

		}

	}

}
