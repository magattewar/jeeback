/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author magat
 */
@Entity
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findById", query = "SELECT l FROM Location l WHERE l.id = :id"),
    @NamedQuery(name = "Location.findByMontant", query = "SELECT l FROM Location l WHERE l.montant = :montant"),
    @NamedQuery(name = "Location.findByOccupe", query = "SELECT l FROM Location l WHERE l.occupe = :occupe"),
    @NamedQuery(name = "Location.findByEtatbien", query = "SELECT l FROM Location l WHERE l.etatbien = :etatbien"),
    @NamedQuery(name = "Location.findByDatedernierpaiem", query = "SELECT l FROM Location l WHERE l.datedernierpaiem = :datedernierpaiem"),
    @NamedQuery(name = "Location.findByDateenregistrement", query = "SELECT l FROM Location l WHERE l.dateenregistrement = :dateenregistrement")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant")
    private float montant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "occupe")
    private boolean occupe;
    @Size(max = 200)
    @Column(name = "etatbien")
    private String etatbien;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datedernierpaiem")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedernierpaiem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateenregistrement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateenregistrement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlocation")
    private List<Loyer> loyerList;
    @JoinColumn(name = "idclient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Client idclient;
    @JoinColumn(name = "idbien", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bien idbien;

    public Location() {
    }

    public Location(Integer id) {
        this.id = id;
    }

    public Location(Integer id, float montant, boolean occupe, Date datedernierpaiem, Date dateenregistrement) {
        this.id = id;
        this.montant = montant;
        this.occupe = occupe;
        this.datedernierpaiem = datedernierpaiem;
        this.dateenregistrement = dateenregistrement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public boolean getOccupe() {
        return occupe;
    }

    public void setOccupe(boolean occupe) {
        this.occupe = occupe;
    }

    public String getEtatbien() {
        return etatbien;
    }

    public void setEtatbien(String etatbien) {
        this.etatbien = etatbien;
    }

    public Date getDatedernierpaiem() {
        return datedernierpaiem;
    }

    public void setDatedernierpaiem(Date datedernierpaiem) {
        this.datedernierpaiem = datedernierpaiem;
    }

    public Date getDateenregistrement() {
        return dateenregistrement;
    }

    public void setDateenregistrement(Date dateenregistrement) {
        this.dateenregistrement = dateenregistrement;
    }

    @XmlTransient
    public List<Loyer> getLoyerList() {
        return loyerList;
    }

    public void setLoyerList(List<Loyer> loyerList) {
        this.loyerList = loyerList;
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
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
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Location[ id=" + id + " ]";
    }
    
}
