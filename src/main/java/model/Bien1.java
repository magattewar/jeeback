/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author magat
 */
public class Bien1 {
    private Integer id;
    private String description;
    private String cnibailleur;
    private float montantvoulu;
    private float prixlocation;
    private String photo;
    private boolean disponibilite;
    private String adresse;
    private int idBailleur;

    public Integer getId() {
        return id;
    }

    public String getCnibailleur() {
        return cnibailleur;
    }

    public void setCnibailleur(String cnibailleur) {
        this.cnibailleur = cnibailleur;
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

    public boolean isDisponibilite() {
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

    public int getIdBailleur() {
        return idBailleur;
    }

    public void setIdBailleur(int idBailleur) {
        this.idBailleur = idBailleur;
    }
    
    

    public Bien1() {
    }
    
    
}
