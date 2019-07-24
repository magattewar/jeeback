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
public class Bailleur2 {
    private int id;
    private String nom;
    private String cni;
    private int nombreproprietes;
    private float credit;
    private int idmodepaiemment;
    private String modepaiement;

    public Bailleur2(int id, String nom, String cni, int nombreproprietes, float credit, int idmodepaiemment, String modepaiement) {
        this.id = id;
        this.nom = nom;
        this.cni = cni;
        this.nombreproprietes = nombreproprietes;
        this.credit = credit;
        this.idmodepaiemment = idmodepaiemment;
        this.modepaiement = modepaiement;
    }

    public Bailleur2() {
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

    public int getNombreproprietes() {
        return nombreproprietes;
    }

    public void setNombreproprietes(int nombreproprietes) {
        this.nombreproprietes = nombreproprietes;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public int getIdmodepaiemment() {
        return idmodepaiemment;
    }

    public void setIdmodepaiemment(int idmodepaiemment) {
        this.idmodepaiemment = idmodepaiemment;
    }

    public String getModepaiement() {
        return modepaiement;
    }

    public void setModepaiement(String modepaiement) {
        this.modepaiement = modepaiement;
    }
    
    
}
