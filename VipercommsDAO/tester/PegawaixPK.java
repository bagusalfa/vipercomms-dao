package tester;

import viper.comms.dao.anno.IdMeth;

public class PegawaixPK {
	private int Id;

	public PegawaixPK(int id, String kode) {
		this.Id = id;
		this.Kode = kode;
	}

	public PegawaixPK() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int locId) {
		// prepArray(this,"ID", locId);

		Id = locId;
	}

	private String Kode;

	public String getKode() {
		return Kode;
	}

	public void setKode(String kode) {
		Kode = kode;
	}

}
