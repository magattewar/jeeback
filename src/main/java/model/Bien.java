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
import javax.persistence.Lob;
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
@Table(name = "bien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bien.findAll", query = "SELECT b FROM Bien b"),
    @NamedQuery(name = "Bien.findById", query = "SELECT b FROM Bien b WHERE b.id = :id"),
    @NamedQuery(name = "Bien.findByDescription", query = "SELECT b FROM Bien b WHERE b.description = :description"),
    @NamedQuery(name = "Bien.findByMontantvoulu", query = "SELECT b FROM Bien b WHERE b.montantvoulu = :montantvoulu"),
    @NamedQuery(name = "Bien.findByPrixlocation", query = "SELECT b FROM Bien b WHERE b.prixlocation = :prixlocation"),
    @NamedQuery(name = "Bien.findByDisponibilite", query = "SELECT b FROM Bien b WHERE b.disponibilite = :disponibilite"),
    @NamedQuery(name = "Bien.findByAdresse", query = "SELECT b FROM Bien b WHERE b.adresse = :adresse"),
    @NamedQuery(name = "Bien.findByDateenregistrement", query = "SELECT b FROM Bien b WHERE b.dateenregistrement = :dateenregistrement"),
    @NamedQuery(name = "Bien.findByDatedernierpaiement", query = "SELECT b FROM Bien b WHERE b.datedernierpaiement = :datedernierpaiement")})
public class Bien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montantvoulu")
    private float montantvoulu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prixlocation")
    private float prixlocation;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "photo")
    private String photo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disponibilite")
    private boolean disponibilite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateenregistrement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateenregistrement;
    @Column(name = "datedernierpaiement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedernierpaiement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbien")
    private List<Etatbien> etatbienList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbien")
    private List<Location> locationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbien")
    private List<Creditbailleur> creditbailleurList;
    @JoinColumn(name = "idbailleur", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bailleur idbailleur;

    public Bien() {
    }

    public Bien(Integer id) {
        this.id = id;
    }

    public Bien(Integer id, String description, float montantvoulu, float prixlocation, String photo, boolean disponibilite, String adresse, Date dateenregistrement) {
        this.id = id;
        this.description = description;
        this.montantvoulu = montantvoulu;
        this.prixlocation = prixlocation;
        this.photo = photo;
        this.disponibilite = disponibilite;
        this.adresse = adresse;
        this.dateenregistrement = dateenregistrement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMontantvoulu() {
        return montantvoulu;
    }

    public void setMontantvoulu(float montantvoulu) {
        this.montantvoulu = montantvoulu;
    }

    public float getPrixlocation() {
        return prixlocation;
    }

    public void setPrixlocation(float prixlocation) {
        this.prixlocation = prixlocation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateenregistrement() {
        return dateenregistrement;
    }

    public void setDateenregistrement(Date dateenregistrement) {
        this.dateenregistrement = dateenregistrement;
    }

    public Date getDatedernierpaiement() {
        return datedernierpaiement;
    }

    public void setDatedernierpaiement(Date datedernierpaiement) {
        this.datedernierpaiement = datedernierpaiement;
    }

    @XmlTransient
    public List<Etatbien> getEtatbienList() {
        return etatbienList;
    }

    public void setEtatbienList(List<Etatbien> etatbienList) {
        this.etatbienList = etatbienList;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @XmlTransient
    public List<Creditbailleur> getCreditbailleurList() {
        return creditbailleurList;
    }

    public void setCreditbailleurList(List<Creditbailleur> creditbailleurList) {
        this.creditbailleurList = creditbailleurList;
    }

    public Bailleur getIdbailleur() {
        return idbailleur;
    }

    public void setIdbailleur(Bailleur idbailleur) {
        this.idbailleur = idbailleur;
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
        if (!(object instanceof Bien)) {
            return false;
        }
        Bien other = (Bien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bien[ id=" + id + " ]";
    }
    
}
