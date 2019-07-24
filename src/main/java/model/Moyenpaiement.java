/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author magat
 */
@Entity
@Table(name = "moyenpaiement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moyenpaiement.findAll", query = "SELECT m FROM Moyenpaiement m"),
    @NamedQuery(name = "Moyenpaiement.findById", query = "SELECT m FROM Moyenpaiement m WHERE m.id = :id"),
    @NamedQuery(name = "Moyenpaiement.findByLibelle", query = "SELECT m FROM Moyenpaiement m WHERE m.libelle = :libelle")})
public class Moyenpaiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(mappedBy = "modepaiement")
    private List<Bailleur> bailleurList;

    public Moyenpaiement() {
    }

    public Moyenpaiement(Integer id) {
        this.id = id;
    }

    public Moyenpaiement(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<Bailleur> getBailleurList() {
        return bailleurList;
    }

    public void setBailleurList(List<Bailleur> bailleurList) {
        this.bailleurList = bailleurList;
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
        if (!(object instanceof Moyenpaiement)) {
            return false;
        }
        Moyenpaiement other = (Moyenpaiement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Moyenpaiement[ id=" + id + " ]";
    }
    
}
