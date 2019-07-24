/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author magat
 */
@Entity
@Table(name = "bailleur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bailleur.findAll", query = "SELECT b FROM Bailleur b"),
    @NamedQuery(name = "Bailleur.findById", query = "SELECT b FROM Bailleur b WHERE b.id = :id"),
    @NamedQuery(name = "Bailleur.findByNom", query = "SELECT b FROM Bailleur b WHERE b.nom = :nom"),
    @NamedQuery(name = "Bailleur.findByCni", query = "SELECT b FROM Bailleur b WHERE b.cni = :cni")})
public class Bailleur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cni")
    private int cni;
    @JoinColumn(name = "modepaiement", referencedColumnName = "id")
    @ManyToOne
    private Moyenpaiement modepaiement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbailleur")
    private List<Bien> bienList;

    public Bailleur() {
    }

    public Bailleur(Integer id) {
        this.id = id;
    }

    public Bailleur(Integer id, String nom, int cni) {
        this.id = id;
        this.nom = nom;
        this.cni = cni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCni() {
        return cni;
    }

    public void setCni(int cni) {
        this.cni = cni;
    }

    public Moyenpaiement getModepaiement() {
        return modepaiement;
    }

    public void setModepaiement(Moyenpaiement modepaiement) {
        this.modepaiement = modepaiement;
    }

    @XmlTransient
    public List<Bien> getBienList() {
        return bienList;
    }

    public void setBienList(List<Bien> bienList) {
        this.bienList = bienList;
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
        if (!(object instanceof Bailleur)) {
            return false;
        }
        Bailleur other = (Bailleur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bailleur[ id=" + id + " ]";
    }
    
}
