package viper.comms.dao.conn;

public class RowDetails {
private Row myBaris;
private FilterOperation param=FilterOperation.UNKNOWN;
public Row getMyBaris() {
	return myBaris;
}
public void setMyBaris(Row myBaris) {
	this.myBaris = myBaris;
}

public void addBaris(String column,Object columnValue){
	if (myBaris==null){
		myBaris=new Row();
	}
	myBaris.addColumn(column, columnValue);
}
public void addParam(FilterOperation paramOperation){
	param=paramOperation;
}
public FilterOperation getParam() {
	return param;
}
public void setParam(FilterOperation param) {
	this.param = param;
}
}
