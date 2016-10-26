package tester;
import viper.comms.dao.anno.ColumnMeth;
import viper.comms.dao.anno.IdMeth;
import viper.comms.dao.anno.TemporalType;
import viper.comms.dao.conn.AbstractEntityManager;
import viper.comms.dao.conn.EmbeddedId;

import java.io.Serializable;
import viper.comms.dao.anno.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

/**
 *
 * @author alva
 */
@Entity
@Table(name = "PEGAWAIX")
@NamedQueries({@NamedQuery(name = "Pegawaix.findAll", query = "SELECT p FROM Pegawaix p"), @NamedQuery(name = "Pegawaix.findById", query = "SELECT p FROM Pegawaix p WHERE p.pegawaixPK.id = :id"), @NamedQuery(name = "Pegawaix.findByKode", query = "SELECT p FROM Pegawaix p WHERE p.pegawaixPK.kode = :kode"), @NamedQuery(name = "Pegawaix.findByNama", query = "SELECT p FROM Pegawaix p WHERE p.nama = :nama"), @NamedQuery(name = "Pegawaix.findByLahir", query = "SELECT p FROM Pegawaix p WHERE p.lahir = :lahir")})
public class Pegawaix implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PegawaixPK pegawaixPK;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "LAHIR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lahir;

    public Pegawaix() {
    }

    public Pegawaix(PegawaixPK pegawaixPK) {
        this.pegawaixPK = pegawaixPK;
    }

    public Pegawaix(int id, String kode) {
        this.pegawaixPK = new PegawaixPK(id, kode);
    }

    public PegawaixPK getPegawaixPK() {
        return pegawaixPK;
    }

    public void setPegawaixPK(PegawaixPK pegawaixPK) {
        this.pegawaixPK = pegawaixPK;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getLahir() {
        return lahir;
    }

    public void setLahir(Date lahir) {
        this.lahir = lahir;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pegawaixPK != null ? pegawaixPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pegawaix)) {
            return false;
        }
        Pegawaix other = (Pegawaix) object;
        if ((this.pegawaixPK == null && other.pegawaixPK != null) || (this.pegawaixPK != null && !this.pegawaixPK.equals(other.pegawaixPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication11.Pegawaix[pegawaixPK=" + pegawaixPK + "]";
    }

}
