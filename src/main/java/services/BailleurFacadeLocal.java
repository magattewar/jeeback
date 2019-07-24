/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import model.Bailleur;

/**
 *
 * @author magat
 */
@Local
public interface BailleurFacadeLocal {

    void create(Bailleur bailleur);

    void edit(Bailleur bailleur);

    void remove(Bailleur bailleur);

    Bailleur find(Object id);

    List<Bailleur> findAll();

    List<Bailleur> findRange(int[] range);

    int count();
    

    public Bailleur finByCni(int parameter);
    
    public List<Float> credit(int idbailleur);

    public boolean regler(int id);
    
}
