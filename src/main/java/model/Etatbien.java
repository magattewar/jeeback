/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author magat
 */
@Entity
@Table(name = "etatbien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etatbien.findAll", query = "SELECT e FROM Etatbien e"),
    @NamedQuery(name = "Etatbien.findById", query = "SELECT e FROM Etatbien e WHERE e.id = :id"),
    @NamedQuery(name = "Etatbien.findByDate", query = "SELECT e FROM Etatbien e WHERE e.date = :date"),
    @NamedQuery(name = "Etatbien.findByLibelle", query = "SELECT e FROM Etatbien e WHERE e.libelle = :libelle")})
public class Etatbien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "libelle")
    private String libelle;
    @JoinColumn(name = "idbien", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bien idbien;

    public Etatbien() {
    }

    public Etatbien(Integer id) {
        this.id = id;
    }

    public Etatbien(Integer id, Date date, String libelle) {
        this.id = id;
        this.date = date;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Bien getIdbien() {
        return idbien;
    }

    public void setIdbien(Bien idbien) {
        this.idbien = idbien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etatbien)) {
            return false;
        }
        Etatbien other = (Etatbien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Etatbien[ id=" + id + " ]";
    }
    
}
