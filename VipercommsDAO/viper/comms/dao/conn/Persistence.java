package viper.comms.dao.conn;

import java.util.Map;

public abstract class Persistence extends EntityManagerFactory{

public static EntityManagerFactory createEntityManagerFactory(String string, Map map) {
	return new EntityManagerFactory(string,map);
}

public static EntityManagerFactory createEntityManagerFactory() {
	return new EntityManagerFactory();
}

public static EntityManagerFactory createEntityManagerFactory(String string) {
	// TODO Auto-generated method stub
	return new EntityManagerFactory();
}

}
