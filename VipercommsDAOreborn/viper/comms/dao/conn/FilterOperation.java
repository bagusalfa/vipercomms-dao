/**
 * 
 */
package viper.comms.dao.conn;

/**
 * @author Administrator
 *
 */
public enum FilterOperation {
OR_TYPE,
AND_TYPE,
AND_LIKE_TYPE,
OR_LIKE_TYPE,
LIKE,
IS_NULL,
IS_NOT_NULL,
GREATER_THAN,
LESS_THAN,
GREATER_THAN_OR_EQUAL,
LESS_THAN_OR_EQUAL,
NOT_EQUAL,
UNKNOWN;
}
