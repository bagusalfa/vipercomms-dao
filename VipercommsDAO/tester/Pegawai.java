package tester;
import viper.comms.dao.anno.Column;
import viper.comms.dao.anno.ColumnMeth;
import viper.comms.dao.anno.Id;
import viper.comms.dao.anno.IdMeth;
import viper.comms.dao.conn.AbstractEntityManager;

import java.io.Serializable;
@SuppressWarnings("serial")
//public class Pegawai extends AbstractEntityManager  implements Serializable   {
	public class Pegawai   implements Serializable   {
         @Id private int Id;
        /* @IdMeth*/	public int getId() {
				return Id;
			}
			public void setId(int locId) {
			//prepArray(this,"ID", locId);
			
				Id = locId;
			}
         @Column(name="NAMA") private String Nama;
         /* @ColumnMeth*/ public String getNama() {
				return Nama;
			}
			public void setNama(String locNama) {
			//prepArray(this,"NAMA", locNama);
			
				Nama = locNama;
			}
         @Column(name="LAHIR") private java.util.Date Lahir;
         /*@ColumnMeth*/ public java.util.Date getLahir() {
				return Lahir;
			}
			public void setLahir(java.util.Date locLahir) {
			//prepArray(this,"LAHIR", locLahir);
			
				Lahir = locLahir;
			}
//		public boolean LoadFromRow(Row myRow){
//			if (!(myRow.getColumnValue("ID")
//==null)){
//			setId((Integer)myRow.getColumnValue("ID")
//);
//			}
//			if (!(myRow.getColumnValue("NAMA")
//==null)){
//			setNama((String)myRow.getColumnValue("NAMA")
//);
//			}
//			if (!(myRow.getColumnValue("LAHIR")
//==null)){
//			setLahir((java.util.Date)myRow.getColumnValue("LAHIR")
//);
//			}
//			return true;
//		}
			
			
			
			}
