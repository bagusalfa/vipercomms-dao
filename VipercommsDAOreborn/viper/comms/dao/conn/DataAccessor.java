package viper.comms.dao.conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.DataFormatException;

import viper.comms.dao.cache.Key;
import viper.comms.dao.exception.DataException;
import viper.comms.dao.exception.EntityNotFoundException;



public interface DataAccessor {

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
     * @param operation 
    @return             The list of rows.
    Melakukan loading, dengan Strict Filter
    Trying And / Or in filtering
    **/
    List read(String table,
              String[] columns,
              Row selectionRow,
              String[] sortColumns, FilterOperation operation) throws DataFormatException;

    /**
    Inserts data into a table.

    @param table        The table.
    @param rows         The rows to insert.
    Insert - standard
     * @throws EntityNotFoundException 
     * @throws DataException 
    **/
    boolean insert(String table, List rows) throws DataFormatException,  DataException;

    /**
    Updates data in a table.

    @param table        The table.
     * @param selectionRow A set of filter columns and values
                        used to subset the rows, or null to
                        update all of the rows in the table.
     * @param updateRow    A set of update columns and values.
     * @param operation 
     * Update with strict filter
     * Trying And / Or in filtering
    **/
    boolean update(String table,
                Row selectionRow,
                Row updateRow, FilterOperation operation) throws DataFormatException;

    /**
    Deletes data from a table.

    @param table        The table.
     * @param selectionRow A set of filter columns and values
                        used to subset the rows, or null to
                        delete all of the rows in the table.
     * @param operation 
     * Delete in strict Filter ,
     * Trying And / Or Operation
    **/
    boolean delete(String table, Row selectionRow, FilterOperation operation)
        throws DataFormatException;
    
    /**
     * 
     Read Dynamic with "where clause"
     * @param table
     * @param whereClause
     * @param orderByClause
     * @return
     * @throws SQLException 
     * Read with Dynamic filter (Where, Order)
     * 
     */
    List readDynamic(String table, String whereClause, String orderByClause) throws SQLException;
    
    /**
     * 
     * @param querySQL
     * @throws SQLException
     * Global Non query SQL (CUD) 
     */
    boolean execNonQuery(String querySQL) throws SQLException;
    
    /**
     * 
     * @param querySQL
     * @return
     * @throws SQLException
     * 
     * Global query SQL (R)
     */
    List execQuery(String querySQL) throws SQLException;
    
    /**
     * TODO : Read with 'like' filter, is null , is not null
     * hanya untuk satu parameter
     */
    List readMO(String table,
            String[] columns,
            Row selectionRow,
            String[] sortColumns, FilterOperation operation) throws DataFormatException;
    
    /**
     * TODO : Read with 'like' filter, is null , is not null
     * untuk banyak parameter
     * @throws EntityNotFoundException 
     */
    List readMOExclusiveParam(String table,
            String[] columns,
            Row selectionRow,
            String[] sortColumns,
            FilterOperation operation) throws DataFormatException, EntityNotFoundException;
    
    /**
     * TODO : Update with 'like' filter, is null , is not null
     */
    
    boolean updateMO(String table,
            Row selectionRow,
            Row updateRow, FilterOperation operation) throws DataFormatException;

    /**
     * TODO : Delete with 'like' filter, is null , is not null
     * @throws DataException 
     */
    boolean deleteInExclusiveParam(String table, Row selectionRow, FilterOperation oper)
    throws DataFormatException, DataException;

    boolean updateInExclusiveParam(String table,
            Row selectionRow,
            Row updateRow,FilterOperation operation) throws DataFormatException, DataException;

    
    boolean beginTransaction() throws SQLException;
    boolean commit() throws SQLException;
    boolean rollback() throws SQLException;
    Connection getConnection();
    Transaction getTransaction();
    void usingActiveTransaction(Transaction trans);
    
    /**
    Reads data from a table.

    @param table        The table.
    @param key          The key.
    @return             The list of rows.
    **/
    List read(String table, Key key)
        throws DataException;

}



