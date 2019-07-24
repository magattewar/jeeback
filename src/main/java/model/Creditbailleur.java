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
@Table(name = "creditbailleur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditbailleur.findAll", query = "SELECT c FROM Creditbailleur c"),
    @NamedQuery(name = "Creditbailleur.findById", query = "SELECT c FROM Creditbailleur c WHERE c.id = :id"),
    @NamedQuery(name = "Creditbailleur.findByMontant", query = "SELECT c FROM Creditbailleur c WHERE c.montant = :montant"),
    @NamedQuery(name = "Creditbailleur.findByPaye", query = "SELECT c FROM Creditbailleur c WHERE c.paye = :paye"),
    @NamedQuery(name = "Creditbailleur.findByDate", query = "SELECT c FROM Creditbailleur c WHERE c.date = :date")})
public class Creditbailleur implements Serializable {

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
    @Column(name = "paye")
    private boolean paye;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "idbien", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bien idbien;

    public Creditbailleur() {
    }

    public Creditbailleur(Integer id) {
        this.id = id;
    }

    public Creditbailleur(Integer id, float montant, boolean paye, Date date) {
        this.id = id;
        this.montant = montant;
        this.paye = paye;
        this.date = date;
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

    public boolean getPaye() {
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof Creditbailleur)) {
            return false;
        }
        Creditbailleur other = (Creditbailleur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Creditbailleur[ id=" + id + " ]";
    }
    
}
