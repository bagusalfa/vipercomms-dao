package tester;

public class UjiBunga {

	/**
	 * @param args
	 */
	 public Object iif(boolean statement,Object isTrue,Object isFalse){
	        if (statement){
	            return isTrue;
	        }else{
	            return isFalse;
	        }
	    }
	public double Flat2eff(double mrate, double mlama){
	         double local1, local2, local3, local4, local5, local6, local7;

	    double F_Efek= 0;
	   if (mrate != 0){

	      mrate= mrate / 1200;

	      local1= mrate + (1 / mlama) ;

	      local2= mrate + 0.01;

	      local3= 0.00000000100000000000000;


	      while ( (Math.abs(F_Efek - local2) > local3) && (mrate != 0)){
	         if (F_Efek != 0){
	            local2= F_Efek;
	         }


	        local4= x_pangkat_y((local2 + 1) , Math.round((mlama  )));
	         F_Efek= local1 * (local4 - 1) / local4;
	      }
	   } else{
	      F_Efek= 0;
	   }
	return F_Efek * 1200;
	    }

	public double x_pangkat_y(double x, long l){
		double temp=x;
		for (int i = 0; i < l-1; i++) {
			temp=temp*x;
		}
		return temp;
	}
	public static void main(String[] args) {
		UjiBunga ub=new UjiBunga();

		System.out.println(ub.Flat2eff(16.5,12));

	}


}
