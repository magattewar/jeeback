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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author magat
 */
@Entity
@Table(name = "loyer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loyer.findAll", query = "SELECT l FROM Loyer l"),
    @NamedQuery(name = "Loyer.findById", query = "SELECT l FROM Loyer l WHERE l.id = :id"),
    @NamedQuery(name = "Loyer.findByMontant", query = "SELECT l FROM Loyer l WHERE l.montant = :montant"),
    @NamedQuery(name = "Loyer.findByDateenregistrement", query = "SELECT l FROM Loyer l WHERE l.dateenregistrement = :dateenregistrement"),
    @NamedQuery(name = "Loyer.findByPaye", query = "SELECT l FROM Loyer l WHERE l.paye = :paye")})
public class Loyer implements Serializable {

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
    @Column(name = "dateenregistrement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateenregistrement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paye")
    private boolean paye;
    @JoinColumn(name = "idlocation", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Location idlocation;

    public Loyer() {
    }

    public Loyer(Integer id) {
        this.id = id;
    }

    public Loyer(Integer id, float montant, Date dateenregistrement, boolean paye) {
        this.id = id;
        this.montant = montant;
        this.dateenregistrement = dateenregistrement;
        this.paye = paye;
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

    public Date getDateenregistrement() {
        return dateenregistrement;
    }

    public void setDateenregistrement(Date dateenregistrement) {
        this.dateenregistrement = dateenregistrement;
    }

    public boolean getPaye() {
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }

    public Location getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(Location idlocation) {
        this.idlocation = idlocation;
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
        if (!(object instanceof Loyer)) {
            return false;
        }
        Loyer other = (Loyer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Loyer[ id=" + id + " ]";
    }
    
}
