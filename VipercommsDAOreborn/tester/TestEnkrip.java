package tester;
import viper.comms.util.*;

public class TestEnkrip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Vutil objUtil=new Vutil();
	
		String chiper=objUtil.TestEn("paidi paijowati","ngatimin");
		chiper=objUtil.TestEn(chiper,"ngatimin");
		System.out.println(chiper);
		
		String hasil=objUtil.TestDec(chiper,"ngatimin");
		hasil=objUtil.TestDec(hasil,"ngatimin");
		System.out.println(hasil);
	}

}
