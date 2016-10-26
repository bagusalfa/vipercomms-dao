package viper.comms.dao.conn;

public class ConcreteTransactionContext implements TransactionContext {
	ConcreteTransaction concreteTransaction =null;
	
	public Transaction getTransaction() {
	  
		if (concreteTransaction==null){
		 concreteTransaction = new ConcreteTransaction();}
		return concreteTransaction;
	}

}
