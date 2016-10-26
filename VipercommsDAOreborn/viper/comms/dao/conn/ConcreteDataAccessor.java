package viper.comms.dao.conn;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;


import viper.comms.dao.cache.Key;
import viper.comms.dao.exception.DataException;
import viper.comms.dao.exception.EntityNotFoundException;
import viper.comms.dao.exception.RetryFailedException;
import viper.comms.dao.resource.decorator.LeakDetectionConnectionDecorator;
import viper.comms.dao.resource.decorator.LeakDetectionResultSetDecorator;
import viper.comms.dao.resource.decorator.LeakDetectionStatementDecorator;

public class ConcreteDataAccessor
implements DataAccessor {

	private ResultSet resultSetLocal=null;
	private boolean IsUsingTransaction=false;
	private Transaction transaction;
	TransactionContext context;
	public TransactionContext getContext() {
		return context;
	}
    boolean IsUsingNoResultException=false;
	public boolean isIsUsingNoResultException() {
		return IsUsingNoResultException;
	}

	public void setIsUsingNoResultException(boolean isUsingNoResultException) {
		IsUsingNoResultException = isUsingNoResultException;
	}
	private boolean IsUsingLeakDetector=false;
	private PrintStream outPrint=System.out;
	private boolean IsUsingPooledConnection=true;
	private boolean IsUsingStatementCache=true;
	private boolean IsUsingTimedConnection=false;
	private boolean IsRetryable=false;
	private final int FIVE_SECOND=5000;
	private Connection mainConnection;
	IConnectionPool connectionPool=null;
	SignOn loginObject=null;
	private DateMode dateMode= DateMode.DATETIME;
	private DatabaseDialect dbaseDialect = DatabaseDialect.FIREBIRD;
//	private Connection payrollConnection;
//	private Connection otherConnection;

	/**
    Constructs a ConcreteDataAccessor object.
	 * @throws SQLException 
	 * @throws DataException 
	 */

	public static void checkWarning(Connection conn) throws SQLException{
		SQLWarning warn=conn.getWarnings();
		while (warn!=null){
			System.out.println("SQLstate: "+warn.getSQLState());
			System.out.println("Message: "+warn.getMessage());
			System.out.println("Vendor Code: "+warn.getErrorCode());
			System.out.println("");
			warn=warn.getNextWarning();
		}
	}

	public ConcreteDataAccessor() throws DataFormatException, DataException {
		try {
//			String user = "sysdba";

//			ConnectionPool connectionPool = new ConnectionPool();
//			String url = "jdbc:firebirdsql:localhost/3050:c:/Siap.fdb";
//			String password = "masterkey";
			loginObject=new SignOn();
			String user = loginObject.getUserName();

			//ConnectionPool connectionPool = new ConnectionPool();
			loginObject.setUsingPooledConnection(IsUsingPooledConnection);
			connectionPool=loginObject.getConnectionPool();
			String url = loginObject.getURL();
			String password = loginObject.getPassWord();


			mainConnection= connectionPool.getConnection(url, user, password);
			if (IsUsingLeakDetector){
				mainConnection=new LeakDetectionConnectionDecorator(mainConnection,outPrint);
			}
			if (IsUsingStatementCache){
				mainConnection= new StatementCachingConnection(mainConnection);
			}
			if (IsUsingTimedConnection){
				mainConnection=new TimedConnection(mainConnection);
			}

//			payrollConnection = connectionPool.getConnection(url, user, password); 
//			otherConnection = connectionPool.getConnection(url, user, password); 
			//Connection connection= connectionPool.getConnection(url, user, password);

			//mainConnection
			//    = DriverManager.getConnection(" ");
			//payrollConnection
			//    = DriverManager.getConnection(" ");
			//otherConnection
			//    = DriverManager.getConnection(" ");
		}
		catch(SQLException e) {
			throw new DataException(
					"Unable to construct DataAccessor", e);
		}
	}

	public ConcreteDataAccessor(SignOn loginObject) throws DataException {
		try {
			this.loginObject=loginObject;
			String user = loginObject.getUserName();

			//ConnectionPool connectionPool = new ConnectionPool();
			connectionPool=loginObject.getConnectionPool();
			String url = loginObject.getURL();
			String password = loginObject.getPassWord();


			mainConnection= connectionPool.getConnection(url, user, password);
			if (IsUsingLeakDetector){
				mainConnection=new LeakDetectionConnectionDecorator(mainConnection,outPrint);
			}
			if (IsUsingStatementCache){			
				mainConnection= new StatementCachingConnection(mainConnection);
			}

//			payrollConnection = connectionPool.getConnection(url, user, password); 
//			otherConnection = connectionPool.getConnection(url, user, password); 
			//Connection connection= connectionPool.getConnection(url, user, password);

			//mainConnection
			//    = DriverManager.getConnection(" ");
			//payrollConnection
			//    = DriverManager.getConnection(" ");
			//otherConnection
			//    = DriverManager.getConnection(" ");
		}
		catch(SQLException e) {
			throw new DataException(
					"Unable to construct DataAccessor", e);
		}
	}

	/**
    Reads data from a table.

    @param table        The table.
	 * @param columns      The columns to read, or null to read
                        all the columns in the table.
	 * @param selectionRow A set of filter columns and values
                        used to subset the rows, or null to
                        read all the rows in the table.
	 * @param sortColumns  The columns to sort, or null to read
                        without sorting.
    @return             The list of rows.
	 **/
	public List read(String table,
			String[] columns,
			Row selectionRow,
			String[] sortColumns, FilterOperation operation)
	throws DataFormatException {
		StringBuffer buffer;
		try {
			buffer = new StringBuffer();

			// Generate the SQL SELECT statement based on
			// the caller's input.
			buffer.append("SELECT ");

			// List the columns if the caller specified any.
			if (columns != null) {
				for(int i = 0; i < columns.length; ++i) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(SearchInjection.Scan(columns[i]));
				}
			}
			else
				buffer.append(" * ");

			// Include the resolved qualified table name.
			buffer.append(" FROM ");
			buffer.append(resolveQualifiedTable(SearchInjection.Scan(table)));

			// Generate the WHERE clause if the caller
			// specified a selection row.
			if (selectionRow != null) {
				buffer.append(
						generateWhereClause(selectionRow, operation));
			}

			// Generate the ORDER BY clause if the caller
			// specified sort columns.
			if (sortColumns != null) {
				buffer.append(" ORDER BY ");
				for(int i = 0; i < sortColumns.length; ++i) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(SearchInjection.Scan(sortColumns[i]));
					buffer.append(" ASC");
				}
			}

			System.out.println(buffer.toString());
			// Resolve the appropriate connection for this
			// table.
			//Connection connection = resolveConnection(table);

			//------------------- mulai di comment for testing
			Connection connection=null;
			connection=resolveConnection("table");
			Statement statement = null ;
			if (IsUsingTransaction){
				statement=transaction.createStatement();
			}else{
				statement=connection.createStatement();
			}
			if (IsUsingLeakDetector){
				statement=new LeakDetectionStatementDecorator(statement,outPrint);
			}
			synchronized(connection) {
				// Execute the query.
				//Statement statement
				//= connection.createStatement();
				ResultSet resultSet = null;

				if (!IsRetryable){
					resultSet= statement.executeQuery(
							SearchInjection.Scan(buffer.toString()));
				}else{
					QueryOperation retOper = new QueryOperation(statement,SearchInjection.Scan(buffer.toString()));
					Retryer retryer = new Retryer(retOper);

//					Retry the operation indefinitely until
//					it succeeds.  Wait 5 seconds between
//					attempts to give the network a chance
//					to recover from problems.
					retryer.perform(-1, FIVE_SECOND);

//					Process the reslt set.
					resultSet = retOper.getResultSet();

				}
				if (IsUsingLeakDetector){
					resultSet=new LeakDetectionResultSetDecorator(resultSet,outPrint);
				}
				setResultSetLocal(resultSet);
				ResultSetMetaData rsmd
				= resultSet.getMetaData();
				int columnCount = rsmd.getColumnCount();

				// Create a list of result rows based on the
				// contents of the result set.
				List<Row> resultRows = new LinkedList<Row>();
				while(resultSet.next()) {
					Row resultRow = new Row();
					for(int i = 1; i <= columnCount; ++i) {
						resultRow.addColumn(
								rsmd.getColumnName(i),
								resultSet.getObject(i));
					}
					resultRows.add(resultRow);
				}
				checkWarning(connection);
				resultSet.close();
				statement.close();
				return resultRows;
			}
		} catch (SQLException e) {

			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}


			e.printStackTrace();
		}
		//------------------- akhir di comment for testing
		catch (RetryFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
    Resolves the connection based on the table name.
	 */
	private Connection resolveConnection(String table) {
		// These are just arbitrary rules for the sake
		// of this example.
//		if (table.startsWith("A"))
//		return mainConnection;
//		else if (table.startsWith("P"))
//		return payrollConnection;
//		else
//		return otherConnection;
		return mainConnection;
	}

	public List readDynamic(String table, String whereClause, String orderByClause)  {
/*
		//		 Initialize the selection factory.
		SelectionFactory selectionFactory
		    = new ConcreteSelectionFactory();

//		 Use the selection factory to find
//		 a specific VIN.
		String vin = "NKWDE6764SD171396";
		String selection
		    = selectionFactory.newSelection("VIN",vin,FilterOperation.AND_TYPE);

//		 Issue the query.
		ResultSet resultSet
		    = statement.executeQuery("SELECT * FROM VEHICLES WHERE "
		                             + selection);
*/
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Select * from "+SearchInjection.Scan(table)+" where "+SearchInjection.Scan(whereClause)+" order by "+SearchInjection.Scan(orderByClause));
		//------------------- mulai di comment for testing
		Connection connection=resolveConnection("table");
		Statement statement=null;


		synchronized(connection) {
			// Execute the query.
			try {
				if (IsUsingTransaction){
					statement=transaction.createStatement();
				}else{
					statement=connection.createStatement();
				}
				if (IsUsingLeakDetector){
					statement=new LeakDetectionStatementDecorator(statement,outPrint);
				}
				ResultSet resultSet=null;
				if (!IsRetryable){
					resultSet= statement.executeQuery(
							SearchInjection.Scan(buffer.toString()));
				}else{
					QueryOperation retOper = new QueryOperation(statement,SearchInjection.Scan(buffer.toString()));
					Retryer retryer = new Retryer(retOper);

//					Retry the operation indefinitely until
//					it succeeds.  Wait 5 seconds between
//					attempts to give the network a chance
//					to recover from problems.
					retryer.perform(-1, FIVE_SECOND);

//					Process the reslt set.
					resultSet = retOper.getResultSet();

				}
				if (IsUsingLeakDetector){
					resultSet=new LeakDetectionResultSetDecorator(resultSet,outPrint);
				}

				setResultSetLocal(resultSet);
				ResultSetMetaData rsmd
				= resultSet.getMetaData();
				int columnCount = rsmd.getColumnCount();

				// Create a list of result rows based on the
				// contents of the result set.
				List<Row> resultRows = new LinkedList<Row>();
				while(resultSet.next()) {
					Row resultRow = new Row();
					for(int i = 1; i <= columnCount; ++i) {
						resultRow.addColumn(
								rsmd.getColumnName(i),
								resultSet.getObject(i));
					}
					resultRows.add(resultRow);
				}
				checkWarning(connection);
				// Release database resources and return.
				resultSet.close();
				statement.close();
				return resultRows;
			} catch (SQLException e) {
				if (IsUsingTransaction){
					if (transaction!=null){
						try {
							transaction.rollback();
							IsUsingTransaction=false;
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}


				e.printStackTrace();
			} catch (RetryFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	public ResultSet readDynamicRS(String table, String whereClause, String orderByClause)  {

		StringBuffer buffer = new StringBuffer();
		buffer.append("Select * from "+SearchInjection.Scan(table)+" where "+SearchInjection.Scan(whereClause)+" order by "+SearchInjection.Scan(orderByClause));
		//------------------- mulai di comment for testing
		Connection connection=resolveConnection("table");
		Statement statement=null;


		synchronized(connection) {
			// Execute the query.
			try {
				if (IsUsingTransaction){
					statement=transaction.createStatement();
				}else{
					statement=connection.createStatement();
				}
				if (IsUsingLeakDetector){
					statement=new LeakDetectionStatementDecorator(statement,outPrint);
				}

				ResultSet resultSet=null;
				if (!IsRetryable){
					resultSet= statement.executeQuery(
							SearchInjection.Scan(buffer.toString()));
				}else{
					QueryOperation retOper = new QueryOperation(statement,SearchInjection.Scan(buffer.toString()));
					Retryer retryer = new Retryer(retOper);

//					Retry the operation indefinitely until
//					it succeeds.  Wait 5 seconds between
//					attempts to give the network a chance
//					to recover from problems.
					retryer.perform(-1, FIVE_SECOND);

//					Process the reslt set.
					resultSet = retOper.getResultSet();

				}

				if (IsUsingLeakDetector){
					resultSet=new LeakDetectionResultSetDecorator(resultSet,outPrint);
				}

				// Release database resources and return.
//				resultSet.close();
//				statement.close();
				checkWarning(connection);
				return resultSet;
			} catch (SQLException e) {
				if (IsUsingTransaction){
					if (transaction!=null){
						try {
							transaction.rollback();
							IsUsingTransaction=false;
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}


				e.printStackTrace();
			} catch (RetryFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	public boolean execNonQuery(String querySQL)  {
		Connection connection = resolveConnection("table");
		try {
			Statement statement=null;
			if (IsUsingTransaction){
				statement=transaction.createStatement();
			}else{
				statement=connection.createStatement();
			}
			if (IsUsingLeakDetector){
				statement=new LeakDetectionStatementDecorator(statement,outPrint);
			}

			synchronized(connection) {
				// Execute the delete.
				statement.executeUpdate(querySQL);
				checkWarning(connection);
				statement.close();
				return true;
			}
		} catch (SQLException e) {

			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			e.printStackTrace();
			return false;
		}

	} 

	public List<Row> execQuery(String querySQL)  {


		Connection connection=resolveConnection("table");
		try {
			Statement statement=null;

			if (IsUsingTransaction){
				statement=transaction.createStatement();
			}else{
				statement=connection.createStatement();
			}
			if (IsUsingLeakDetector){
				statement=new LeakDetectionStatementDecorator(statement,outPrint);
			}

			synchronized(connection) {
				// Execute the query.
				//Statement statement
				//= connection.createStatement();
				ResultSet resultSet=null;
				if (!IsRetryable){
					resultSet= statement.executeQuery(
							SearchInjection.Scan(querySQL));
				}else{
					QueryOperation retOper = new QueryOperation(statement,SearchInjection.Scan(querySQL));
					Retryer retryer = new Retryer(retOper);

//					Retry the operation indefinitely until
//					it succeeds.  Wait 5 seconds between
//					attempts to give the network a chance
//					to recover from problems.
					retryer.perform(-1, FIVE_SECOND);

//					Process the reslt set.
					resultSet = retOper.getResultSet();

				}
				if (IsUsingLeakDetector){
					resultSet=new LeakDetectionResultSetDecorator(resultSet,outPrint);
				}

				setResultSetLocal(resultSet);
				ResultSetMetaData rsmd
				= resultSet.getMetaData();
				int columnCount = rsmd.getColumnCount();

				// Create a list of result rows based on the
				// contents of the result set.
				List<Row> resultRows = new LinkedList<Row>();
				while(resultSet.next()) {
					Row resultRow = new Row();
					for(int i = 1; i <= columnCount; ++i) {
						resultRow.addColumn(
								rsmd.getColumnName(i),
								resultSet.getObject(i));
					}
					resultRows.add(resultRow);
				}
				checkWarning(connection);
				resultSet.close();
				statement.close();
				return resultRows;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RetryFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet execQueryRS(String querySQL)  {


		Connection connection=resolveConnection("table");
		try {
			Statement statement=null;

			if (IsUsingTransaction){
				statement=transaction.createStatement();
			}else{
				statement=connection.createStatement();
			}
			if (IsUsingLeakDetector){
				statement=new LeakDetectionStatementDecorator(statement,outPrint);
			}

			synchronized(connection) {
				// Execute the query.
				//Statement statement
				//= connection.createStatement();
				ResultSet resultSet=null;
				if (!IsRetryable){
					resultSet= statement.executeQuery(
							SearchInjection.Scan(querySQL));
				}else{
					QueryOperation retOper = new QueryOperation(statement,SearchInjection.Scan(querySQL));
					Retryer retryer = new Retryer(retOper);

//					Retry the operation indefinitely until
//					it succeeds.  Wait 5 seconds between
//					attempts to give the network a chance
//					to recover from problems.
					retryer.perform(-1, FIVE_SECOND);

//					Process the reslt set.
					resultSet = retOper.getResultSet();

				}
//				resultSet.close();
				//statement.close();
				if (IsUsingLeakDetector){
					resultSet=new LeakDetectionResultSetDecorator(resultSet,outPrint);
				}

				checkWarning(connection);
				return resultSet;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RetryFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public boolean deleteInExclusiveParam(String table, Row selectionRow, FilterOperation oper) throws DataFormatException, DataException {
		// TODO Auto-generated method stub
//		Generate the SQL DELETE statement based on the
		// caller's input.
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM ");
		buffer.append(resolveQualifiedTable(table));

		// Generate the WHERE clause if the caller
		// specified a selection row.

		// Generate the WHERE clause if the caller
		// specified a selection row.
		if (selectionRow != null) {
			buffer.append(
					generateWhereClauseMOEP(selectionRow,oper));
		}
		System.out.println(buffer.toString());
//		Resolve the appropriate connection for this
		// table.
		try{
			Connection connection = resolveConnection(table);
			Statement statement=null;

			if (IsUsingTransaction){
				statement=transaction.createStatement();
			}else{
				statement=connection.createStatement();
			}
			if (IsUsingLeakDetector){
				statement=new LeakDetectionStatementDecorator(statement,outPrint);
			}

			synchronized(connection) {
				// Execute the update.

				int resultStatementExecuted=statement.executeUpdate(
						SearchInjection.Scan(buffer.toString()));
				checkWarning(connection);
				statement.close();
				if (resultStatementExecuted==0){
					if (IsUsingNoResultException){
						throw new DataException("Remove failed");
					}
				}
				return true;
			}
		}
		catch(SQLException e) {

			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			throw new DataFormatException(
					"Unable to update table " + table+ e.getMessage());
		}
	}


	public List readMO(String table, String[] columns, Row selectionRow, String[] sortColumns, FilterOperation operation) throws DataFormatException {
		StringBuffer buffer;
		try {
			buffer = new StringBuffer();

			// Generate the SQL SELECT statement based on
			// the caller's input.
			buffer.append("SELECT ");

			// List the columns if the caller specified any.
			if (columns != null) {
				for(int i = 0; i < columns.length; ++i) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(SearchInjection.Scan(columns[i]));
				}
			}
			else
				buffer.append(" * ");

			// Include the resolved qualified table name.
			buffer.append(" FROM ");
			buffer.append(resolveQualifiedTable(SearchInjection.Scan(table)));

			// Generate the WHERE clause if the caller
			// specified a selection row.
			if (selectionRow != null) {
				buffer.append(
						generateWhereClauseMO(selectionRow, operation));
			}

			// Generate the ORDER BY clause if the caller
			// specified sort columns.
			if (sortColumns != null) {
				buffer.append(" ORDER BY ");
				for(int i = 0; i < sortColumns.length; ++i) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(SearchInjection.Scan(sortColumns[i]));
					buffer.append(" ASC");
				}
			}

			System.out.println(buffer.toString());
			// Resolve the appropriate connection for this
			// table.
			//Connection connection = resolveConnection(table);

			//------------------- mulai di comment for testing
			Connection connection=null;
			connection=resolveConnection("table");
			Statement statement = null ;

			synchronized(connection) {
				if (IsUsingTransaction){
					statement=transaction.createStatement();
				}else{
					statement=connection.createStatement();
				}
				if (IsUsingLeakDetector){
					statement=new LeakDetectionStatementDecorator(statement,outPrint);
				}

				// Execute the query.
				//Statement statement
				//= connection.createStatement();
				ResultSet resultSet=null;
				if (!IsRetryable){
					resultSet= statement.executeQuery(
							SearchInjection.Scan(buffer.toString()));
				}else{
					QueryOperation retOper = new QueryOperation(statement,SearchInjection.Scan(buffer.toString()));
					Retryer retryer = new Retryer(retOper);

//					Retry the operation indefinitely until
//					it succeeds.  Wait 5 seconds between
//					attempts to give the network a chance
//					to recover from problems.
					retryer.perform(-1, FIVE_SECOND);

//					Process the reslt set.
					resultSet = retOper.getResultSet();

				}
				if (IsUsingLeakDetector){
					resultSet=new LeakDetectionResultSetDecorator(resultSet,outPrint);
				}

				setResultSetLocal(resultSet);
				ResultSetMetaData rsmd
				= resultSet.getMetaData();
				int columnCount = rsmd.getColumnCount();

				// Create a list of result rows based on the
				// contents of the result set.
				List<Row> resultRows = new LinkedList<Row>();
				while(resultSet.next()) {
					Row resultRow = new Row();
					for(int i = 1; i <= columnCount; ++i) {
						resultRow.addColumn(
								rsmd.getColumnName(i),
								resultSet.getObject(i));
					}
					resultRows.add(resultRow);
				}
				checkWarning(connection);
				resultSet.close();
				statement.close();
				return resultRows;
			}
		} catch (SQLException e) {

			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}


			e.printStackTrace();
		}
		//------------------- akhir di comment for testing
		catch (RetryFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean updateMO(String table, Row selectionRow, Row updateRow, FilterOperation operation) throws DataFormatException {
		return IsUsingTransaction;
		// TODO Auto-generated method stub

	}
//	-- ini kebawah sudah beres


	private String generateWhereClauseMOEP(Row selectionRow,  List<FilterOperation> param, FilterOperation oper) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" WHERE ");
		boolean firstColumn = true;
		int j=-1;
		for(Iterator i=selectionRow.columns(); i.hasNext();){
			j++;
			if (!firstColumn){
				switch (oper){
				case AND_TYPE: buffer.append(" AND ");break;
				case OR_TYPE: buffer.append(" OR ");break;
				case AND_LIKE_TYPE: buffer.append(" AND ");break;
				case OR_LIKE_TYPE: buffer.append(" OR ");break;
				default  : buffer.append(" AND ");break;
				}

			}
			else
				firstColumn = false;

			String column = (String)i.next();

			IsiBaris columnValue
			= (IsiBaris) selectionRow.getColumnValue(column);

			buffer.append(column);

			switch (columnValue.getParam()) {
			case IS_NOT_NULL:
				buffer.append(" is not null ");
				break;
			case IS_NULL:
				buffer.append(" is null ");
				break;
			case LIKE:{
				buffer.append(" like ");
				buffer.append(
						generateLiteralValueLike(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case GREATER_THAN:{
				buffer.append(" > ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case GREATER_THAN_OR_EQUAL:{
				buffer.append(" >= ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case LESS_THAN:{
				buffer.append(" < ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case LESS_THAN_OR_EQUAL :{
				buffer.append(" <= ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case NOT_EQUAL:{
				buffer.append(" <> ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			default:{
				buffer.append(" = ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			}




		}

		return buffer.toString();
	}


	private String generateLiteralValueLike(Object literalValue) {
		if (literalValue instanceof String){
			SearchInjection.Scan((String) literalValue);
		}
		StringBuffer buffer = new StringBuffer();
		if (!(literalValue instanceof Number))
			buffer.append("'%");
		if (!(literalValue instanceof java.util.Date)){
			buffer.append(SearchInjection.Scan(literalValue));
		}else{

//			java.util.Date myDate=(Date) literalValue;
//			java.text.SimpleDateFormat objDataFormat = new java.text.SimpleDateFormat("dd-MM-yyyy H:m:s.0000"); //date format for validating
//			String tgl= objDataFormat.format(myDate);
//			buffer.append(tgl);
			buffer.append(FormattingDate((Date) literalValue));
		}
		if (!(literalValue instanceof Number))
			buffer.append("%'");
		return buffer.toString();
	}

	public boolean beginTransaction() throws SQLException {
		
		context=new ConcreteTransactionContext();
		transaction=context.getTransaction();
		transaction.setConnection(mainConnection);
		transaction.begin();
		IsUsingTransaction=true;
		return true;

	}

	public boolean commit() throws SQLException {
		transaction.commit();
		IsUsingTransaction=false;
		return true;

	}

	public boolean rollback() throws SQLException {
		transaction.rollback();
		IsUsingTransaction=false;	
		return true;
	}

	public Connection getConnection() {
		return mainConnection;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void usingActiveTransaction(Transaction trans) {
		if (trans!=null){
			transaction=trans;
			IsUsingTransaction=true;
		}
	}
	private String generateWhereClauseMOEP(Row selectionRow,  FilterOperation oper) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(" WHERE ");
		boolean firstColumn = true;
		int j=-1;
		for(Iterator i=selectionRow.columns(); i.hasNext();){
			j++;
			if (!firstColumn){
				switch (oper){
				case AND_TYPE: buffer.append(" AND ");break;
				case OR_TYPE: buffer.append(" OR ");break;
				case AND_LIKE_TYPE: buffer.append(" AND ");break;
				case OR_LIKE_TYPE: buffer.append(" OR ");break;
				default  : buffer.append(" AND ");break;
				}

			}
			else
				firstColumn = false;

			String column = (String)i.next();

			IsiBaris columnValue
			= (IsiBaris) selectionRow.getColumnValue(column);

			buffer.append(column);

			switch (columnValue.getParam()) {
			case IS_NOT_NULL:
				buffer.append(" is not null ");
				break;
			case IS_NULL:
				buffer.append(" is null ");
				break;
			case AND_LIKE_TYPE:{
				buffer.append(" like ");
				buffer.append(
						generateLiteralValueLike(columnValue.getMyBaris().getColumnValue(column)));

			}
			case OR_LIKE_TYPE:{
				buffer.append(" like ");
				buffer.append(
						generateLiteralValueLike(columnValue.getMyBaris().getColumnValue(column)));

			}

			case LIKE:{
				buffer.append(" like ");
				buffer.append(
						generateLiteralValueLike(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case GREATER_THAN:{
				buffer.append(" > ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case GREATER_THAN_OR_EQUAL:{
				buffer.append(" >= ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case LESS_THAN:{
				buffer.append(" < ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case LESS_THAN_OR_EQUAL :{
				buffer.append(" <= ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			case NOT_EQUAL:{
				buffer.append(" <> ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			default:{
				buffer.append(" = ");
				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));

			}
			break;
			}




		}

		return buffer.toString();
	}

	public boolean updateInExclusiveParam(String table, Row selectionRow, Row updateRow, FilterOperation operation) throws DataFormatException, DataException {
		// Generate the SQL UPDATE statement based on the
		// caller's input.
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE ");
		buffer.append(resolveQualifiedTable(table));

		// Generate the SET clause.
		buffer.append(" SET ");
		boolean firstColumn = true;
		for(Iterator i=updateRow.columns(); i.hasNext();){
			if (!firstColumn)
				buffer.append(", ");
			else
				firstColumn = false;
			String column = (String)i.next();
			buffer.append(column);
			buffer.append(" = ");
			//Object columnValue
			//= updateRow.getColumnValue(column);
			IsiBaris columnValue
			= (IsiBaris) updateRow.getColumnValue(column);
			//buffer.append(
			//generateLiteralValue(columnValue));
			buffer.append(
					generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));
		}

		// Generate the WHERE clause if the caller
		// specified a selection row.
		if (selectionRow != null) {
			buffer.append(
					generateWhereClauseMOEP(selectionRow,operation));
		}
		System.out.println(buffer.toString());
//		Resolve the appropriate connection for this
		// table.
		try{
			Connection connection = resolveConnection(table);
			Statement statement=null;

			if (IsUsingTransaction){
				statement=transaction.createStatement();
			}else{
				statement=connection.createStatement();
			}
			if (IsUsingLeakDetector){
				statement=new LeakDetectionStatementDecorator(statement,outPrint);
			}

			synchronized(connection) {
				// Execute the update.

				int resultStatementExecuted=statement.executeUpdate(
						SearchInjection.Scan(buffer.toString()));
				checkWarning(connection);
				statement.close();
				if (resultStatementExecuted==0){
					if (IsUsingNoResultException){
						throw new DataException("Edit failed");
					}
				}
			}
			return true;
		}
		catch(SQLException e) {
			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			throw new DataFormatException(
					"Unable to update table " + table+ e.getMessage());
		}
	}

	/**
		    Resolves the qualified table name.
	 */
	private String resolveQualifiedTable(String table) {
		// These are just arbitrary rules for the sake
		// of this example.
//		if (table.startsWith("A"))
//		return "ACCTDATA." + table;
//		else if (table.startsWith("P"))
//		return "PAYROLL." + table;
//		else
//		return table;
		return table;
	}

	private String FormattingDate(Date litVal){
		
		java.util.Date myDate=litVal;
		java.text.SimpleDateFormat objDataFormat = null;

		switch (dateMode) {
		case DATEONLY:
			objDataFormat= new java.text.SimpleDateFormat("yyyy-MM-dd"); //date format for validating
			break;
		case DATETIME:
			objDataFormat= new java.text.SimpleDateFormat("yyyy-MM-dd HH:m:s"); //date format for validating
		default:
			objDataFormat= new java.text.SimpleDateFormat("yyyy-MM-dd HH:m:s"); //date format for validating
			break;
		}
		
		
		String tgl= objDataFormat.format(myDate);
		return tgl;
	}
	
	/**
		    Generates a SQL literal string.
	 */
	private String generateLiteralValue(Object literalValue) {
		if (literalValue instanceof String){
			SearchInjection.Scan((String) literalValue);
		}
		StringBuffer buffer = new StringBuffer();
		if (!(literalValue instanceof Number))
			buffer.append("'");
		if (!(literalValue instanceof java.util.Date)){
			buffer.append(literalValue);
		}else{

//			java.util.Date myDate=(Date) literalValue;
//			java.text.SimpleDateFormat objDataFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:m:s"); //date format for validating
//			String tgl= objDataFormat.format(myDate);
//			buffer.append(tgl);
			buffer.append(FormattingDate((Date) literalValue));
		}
		if (!(literalValue instanceof Number))
			buffer.append("'");
		return buffer.toString();
	}
	private String generateLiteralValueMO(Object literalValue,FilterOperation operation) {
		if (literalValue instanceof String){
			SearchInjection.Scan((String) literalValue);
		}
		StringBuffer buffer = new StringBuffer();

		if (!(literalValue instanceof Number))
		{
			if ((operation==FilterOperation.AND_LIKE_TYPE) || (operation==FilterOperation.OR_LIKE_TYPE)) 
			{
				buffer.append("'%");			
			}else
			{
				buffer.append("'");
			}

		}
		if (!(literalValue instanceof java.util.Date)){
			buffer.append(SearchInjection.Scan(literalValue));
		}else{

//			java.util.Date myDate=(Date) literalValue;
//			java.text.SimpleDateFormat objDataFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:m:s"); //date format for validating
//			String tgl= objDataFormat.format(myDate);
//			buffer.append(tgl);
			buffer.append(FormattingDate((Date) literalValue));
		}
		if (!(literalValue instanceof Number))
		{
			if ((operation==FilterOperation.AND_LIKE_TYPE) || (operation==FilterOperation.OR_LIKE_TYPE)) 
			{
				buffer.append("%'");			
			}else
			{
				buffer.append("'");
			}
		}
		return buffer.toString();
	}
	/**
		    Generates a SQL WHERE clause based on a selection row.
	 * @param oper 
	 */
	private String generateWhereClause(Row selectionRow, FilterOperation oper)  {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" WHERE ");
		boolean firstColumn = true;
		for(Iterator i=selectionRow.columns(); i.hasNext();){
			if (!firstColumn){
				if (oper==FilterOperation.AND_TYPE){
					buffer.append(" AND ");
				}else{
					buffer.append(" OR ");
				}
			}
			else
				firstColumn = false;
			String column = (String)i.next();
			buffer.append(column);
			buffer.append(" = ");
			//Object columnValue
			//= selectionRow.getColumnValue(column);
			IsiBaris columnValue
			= (IsiBaris) selectionRow.getColumnValue(column); 
			//buffer.append(
			//generateLiteralValue(columnValue));
			buffer.append(
					generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));
		}
		return buffer.toString();
	}
	private String generateWhereClauseMO(Row selectionRow, FilterOperation oper)  {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" WHERE ");
		boolean firstColumn = true;
		for(Iterator i=selectionRow.columns(); i.hasNext();){
			if (!firstColumn){
				if (oper==FilterOperation.AND_TYPE)
				{
					buffer.append(" AND ");
				}else if (oper==FilterOperation.OR_TYPE)
				{
					buffer.append(" OR ");
				}else if (oper==FilterOperation.AND_LIKE_TYPE)
				{
					buffer.append(" AND ");
				}else if (oper==FilterOperation.OR_LIKE_TYPE)
				{
					buffer.append(" OR ");
				}

				else
				{
					buffer.append(" AND ");
				}
			}
			else
				firstColumn = false;

			String column = (String)i.next();
			buffer.append(column);
			if ((oper==FilterOperation.IS_NOT_NULL) || (oper==FilterOperation.IS_NULL))
			{
				if (oper==FilterOperation.IS_NULL)
				{
					buffer.append(" is null ");
				}else if (oper==FilterOperation.IS_NOT_NULL)
				{
					buffer.append(" is not null ");
				} 

			}else
			{
				if (oper==FilterOperation.AND_LIKE_TYPE)
				{
					buffer.append(" Like ");
				}
				Object columnValue
				= selectionRow.getColumnValue(column);
				buffer.append(
						generateLiteralValueMO(columnValue,oper));

			}
		}
		return buffer.toString();
	}
	/**
    Inserts data into a table.

    @param table        The table.
    @param rows         The rows to insert.
	 * @throws EntityNotFoundException 
	 * @throws DataException 
	 **/
	public boolean insert(String table,
			List rows) throws DataFormatException,  DataException  {
		StringBuffer buffer = new StringBuffer();

		try {
			for(Iterator i = rows.iterator(); i.hasNext(); ) {
				Row row = (Row)i.next();

				// Generate the SQL INSERT statement based on
				// the caller's input.
				buffer.append("INSERT INTO ");
				buffer.append(resolveQualifiedTable(table));

				// List the column names.
				buffer.append(" (");
				boolean firstColumn = true;
				for(Iterator j = row.columns(); j.hasNext();){
					if (!firstColumn)
						buffer.append(", ");
					else
						firstColumn = false;
					buffer.append(j.next());
				}

				// List the column values.
				buffer.append(") VALUES (");
				firstColumn = true;
				for(Iterator j = row.columns(); j.hasNext();){
					if (!firstColumn)
						buffer.append(", ");
					else
						firstColumn = false;

					String column = (String)j.next();
//					Object columnValue
//					= row.getColumnValue(column);
//					buffer.append(
//					generateLiteralValue(columnValue));
					IsiBaris  columnValue
					= (IsiBaris)row.getColumnValue(column);
					buffer.append(
							generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));
				}
				buffer.append(")");

				System.out.println(buffer.toString());
				// Resolve the appropriate connection for
				// this table.
				Connection connection
				= resolveConnection(table);
				Statement statement=null;
				if (IsUsingTransaction){
					statement=transaction.createStatement();
				}else{
					statement=connection.createStatement();
				}
				if (IsUsingLeakDetector){
					statement=new LeakDetectionStatementDecorator(statement,outPrint);
				}

				synchronized(connection) {
					// Execute the insert.
					int resultStatementExecuted=statement.executeUpdate(
							SearchInjection.Scan(buffer.toString()));
					checkWarning(connection);
					statement.close();
					if (resultStatementExecuted==0){
						if (IsUsingNoResultException){
							throw new DataException("Insert failed");
						}
					}
				}
			}

			return true;
		}
		catch(SQLException e) {
			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			throw new DataFormatException(
					buffer.toString()+"Unable to insert into table " + table + e.getMessage());
		}
	}

	/**
    Updates data in a table.

    @param table        The table.
	 * @param selectionRow A set of filter columns and values
                        used to subset the rows, or null to
                        update all the rows in the table.
	 * @param updateRow    A set of update columns and values.
	 **/
	public boolean update(String table,
			Row selectionRow,
			Row updateRow, FilterOperation operation) throws DataFormatException {
		try {
			// Generate the SQL UPDATE statement based on the
			// caller's input.
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE ");
			buffer.append(resolveQualifiedTable(table));

			// Generate the SET clause.
			buffer.append(" SET ");
			boolean firstColumn = true;
			for(Iterator i=updateRow.columns(); i.hasNext();){
				if (!firstColumn)
					buffer.append(", ");
				else
					firstColumn = false;
				String column = (String)i.next();
				buffer.append(column);
				buffer.append(" = ");
				//Object columnValue
				//    = updateRow.getColumnValue(column);
				IsiBaris  columnValue
				= (IsiBaris)updateRow.getColumnValue(column);

				buffer.append(
						generateLiteralValue(columnValue.getMyBaris().getColumnValue(column)));
			}

			System.out.println(buffer.toString());
			// Generate the WHERE clause if the caller
			// specified a selection row.
			if (selectionRow != null) {
				buffer.append(
						generateWhereClause(selectionRow, operation));
			}

			// Resolve the appropriate connection for this
			// table.
			Connection connection = resolveConnection(table);
			Statement statement=null;

			if (IsUsingTransaction){
				statement=transaction.createStatement();
			}else{
				statement=connection.createStatement();
			}
			if (IsUsingLeakDetector){
				statement=new LeakDetectionStatementDecorator(statement,outPrint);
			}

			synchronized(connection) {
				// Execute the update.

				statement.executeUpdate(
						SearchInjection.Scan(buffer.toString()));
				checkWarning(connection);
				statement.close();
			}
			return true;
		}
		catch(SQLException e) {
			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			throw new DataFormatException(
					"Unable to update table " + table+ e.getMessage());
		}
	}
	/**
    Deletes data from a table.

    @param table        The table.
	 * @param selectionRow A set of filter columns and values
                        used to subset the rows, or null to
                        delete all the rows from the table.
	 **/
	public boolean delete(String table,
			Row selectionRow, FilterOperation operation) throws DataFormatException{
		try  {
			// Generate the SQL DELETE statement based on the
			// caller's input.
			StringBuffer buffer = new StringBuffer();
			buffer.append("DELETE FROM ");
			buffer.append(resolveQualifiedTable(table));

			// Generate the WHERE clause if the caller
			// specified a selection row.
			if (selectionRow != null) {
				buffer.append(
						generateWhereClause(selectionRow, operation));
			}

			System.out.println(buffer.toString());
			// Resolve the appropriate connection for
			// this table.
			Connection connection = resolveConnection(table);
			Statement statement=null;
			if (IsUsingLeakDetector){
				statement=new LeakDetectionStatementDecorator(statement,outPrint);
			}

			synchronized(connection) {
				// Execute the update.

				if (IsUsingTransaction){
					statement=transaction.createStatement();
				}else{
					statement=connection.createStatement();
				}
				statement.executeUpdate(
						SearchInjection.Scan(buffer.toString()));
				checkWarning(connection);
				statement.close();
			}
			return true;
		}
		catch(SQLException e) {
			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			throw new DataFormatException(
					"Unable to update table " + table+ e.getMessage());
		}
	}
	public List readMOExclusiveParam(String table, String[] columns, Row selectionRow, String[] sortColumns, FilterOperation operation) throws DataFormatException, EntityNotFoundException {

		try {
			StringBuffer buffer = new StringBuffer();

			// Generate the SQL SELECT statement based on
			// the caller's input.
			buffer.append("SELECT ");
			// List the columns if the caller specified any.
			if (columns != null) {
				for(int i = 0; i < columns.length; ++i) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(columns[i]);
				}
			}
			else
				buffer.append(" * ");

			// Include the resolved qualified table name.
			buffer.append(" FROM ");
			buffer.append(resolveQualifiedTable(table));

			// Generate the WHERE clause if the caller
			// specified a selection row.
			if (selectionRow != null) {
				buffer.append(
						generateWhereClauseMOEP(selectionRow,operation));
			}

			// Generate the ORDER BY clause if the caller
			// specified sort columns.
			if (sortColumns != null) {
				buffer.append(" ORDER BY ");
				for(int i = 0; i < sortColumns.length; ++i) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(sortColumns[i]);
					buffer.append(" ASC");
				}
			}
			System.out.println(buffer.toString());
			// Resolve the appropriate connection for this
			// table.
			//Connection connection = resolveConnection(table);

			//------------------- mulai di comment for testing
			Connection connection=null;
			connection=resolveConnection("table");
			Statement statement = null ;

			synchronized(connection) {
				// Execute the query.
				if (IsUsingTransaction){
					statement=transaction.createStatement();
				}else{
					statement=connection.createStatement();
				}
				if (IsUsingLeakDetector){
					statement=new LeakDetectionStatementDecorator(statement,outPrint);
				}

				//Statement statement
				//= connection.createStatement();
				ResultSet resultSet
				= statement.executeQuery(
						SearchInjection.Scan(buffer.toString()));
				
				if (IsUsingLeakDetector){
					resultSet=new LeakDetectionResultSetDecorator(resultSet,outPrint);
				}

				setResultSetLocal(resultSet);
				ResultSetMetaData rsmd
				= resultSet.getMetaData();
				int columnCount = rsmd.getColumnCount();

				// Create a list of result rows based on the
				// contents of the result set.
				List<Row> resultRows = new LinkedList<Row>();
				boolean emptyResultSet=true;
				while(resultSet.next()) {
					Row resultRow = new Row();
					for(int i = 1; i <= columnCount; ++i) {
						resultRow.addColumn(
								rsmd.getColumnName(i),
								resultSet.getObject(i));
					}
					resultRows.add(resultRow);
					emptyResultSet=false;
				}
				
				checkWarning(connection);
				resultSet.close();
				statement.close();
				if (emptyResultSet){
					if (IsUsingNoResultException){
						throw new EntityNotFoundException("Null result");
					}
				}
				return resultRows;
			}
		} catch (SQLException e) {

			if (IsUsingTransaction){
				if (transaction!=null){
					try {
						transaction.rollback();
						IsUsingTransaction=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}


			e.printStackTrace();
		}
		//------------------- akhir di comment for testing

		return null;
	}

	public ResultSet getResultSetLocal() {
		return resultSetLocal;
	}

	private void setResultSetLocal(ResultSet resultSetLocal) {
		this.resultSetLocal = resultSetLocal;
	}

	public boolean isUsingLeakDetector() {
		return IsUsingLeakDetector;
	}

	public void setUsingLeakDetector(boolean isUsingLeakDetector) {
		IsUsingLeakDetector = isUsingLeakDetector;
	}

	public PrintStream getOutPrint() {
		return outPrint;
	}

	public void setOutPrint(PrintStream outPrint) {
		this.outPrint = outPrint;
	}

	public boolean isUsingPooledConnection() {
		return IsUsingPooledConnection;
	}

	public void setUsingPooledConnection(boolean isUsingPooledConnection) {
		IsUsingPooledConnection = isUsingPooledConnection;
	}

	public boolean isUsingStatementCache() {
		return IsUsingStatementCache;
	}

	public void setUsingStatementCache(boolean isUsingStatementCache) {
		IsUsingStatementCache = isUsingStatementCache;
	}

	public boolean isUsingTimedConnection() {
		return IsUsingTimedConnection;
	}

	public void setUsingTimedConnection(boolean isUsingTimedConnection) {
		IsUsingTimedConnection = isUsingTimedConnection;
	}

	public boolean isRetryable() {
		return IsRetryable;
	}

	public void setRetryable(boolean isRetryable) {
		IsRetryable = isRetryable;
	}

	public List read(String table, Key key)
    throws DataException {

    try {
        // Generate the SQL SELECT statement based on
        // the key.
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM ");
        buffer.append(table);
        boolean firstColumn = true;
        for(Iterator i = key.columnNames();
            i.hasNext(); ) {

            String columnName = (String)i.next();
            Object columnValue = key.get(columnName);

            if (firstColumn) {
                buffer.append(" WHERE ");
                firstColumn = false;
            }
            else {
                buffer.append(" AND ");
            }

            buffer.append(columnName);
            buffer.append(" = ");
            if (!(columnValue instanceof Number))
                buffer.append("'");
            buffer.append(columnValue);
            if (!(columnValue instanceof Number))
                buffer.append("'");
        }

        // Execute the read operation.
        Statement statement
            = mainConnection.createStatement();
        ResultSet resultSet
            = statement.executeQuery(
            buffer.toString());
        ResultSetMetaData rsmd
            = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        // Create a list of data based on the
        // contents of the result set.
        List data = new LinkedList();
        while(resultSet.next()) {
            Row row = new Row();
            for(int i = 1; i <= columnCount; ++i) {
                row.addColumn(
                    rsmd.getColumnName(i),
                    resultSet.getObject(i));
            }
            data.add(row);
        }

        // Release the database resources.
        resultSet.close();
        statement.close();

        return data;
    }
    catch(SQLException e) {
        throw new DataException(e);
    }
}

	public DateMode getDateMode() {
		return dateMode;
	}

	public void setDateMode(DateMode dateMode) {
		this.dateMode = dateMode;
	}

	public DatabaseDialect getDbaseDialect() {
		return dbaseDialect;
	}

	public void setDbaseDialect(DatabaseDialect dbaseDialect) {
		this.dbaseDialect = dbaseDialect;
	}


}




