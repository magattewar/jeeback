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
public class Location2 {
    private int id;
    private int idbien;
    private int idclient;
    private float montant;
    private boolean occupe;
    private String etatbien;

    public Location2() {
    }

    public Location2(int id, int idbien, int idclient, float montant, boolean occupe, String etatbien) {
        this.id = id;
        this.idbien = idbien;
        this.idclient = idclient;
        this.montant = montant;
        this.occupe = occupe;
        this.etatbien = etatbien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdbien() {
        return idbien;
    }

    public void setIdbien(int idbien) {
        this.idbien = idbien;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public boolean isOccupe() {
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
    
    
}
