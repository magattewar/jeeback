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
public class Client1 {
    private int id;
    private String nom;
    private String cni;
    private String telephone;
    private float montantdu;

    public Client1(int id, String nom, String cni, float montantdu) {
        this.id = id;
        this.nom = nom;
        this.cni = cni;
        this.montantdu = montantdu;
        
    }

    
    
    public float getMontantdu() {
        return montantdu;
    }

    public void setMontantdu(float montantdu) {
        this.montantdu = montantdu;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public Client1() {
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
}
