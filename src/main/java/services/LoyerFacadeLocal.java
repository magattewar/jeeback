/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import model.Loyer;

/**
 *
 * @author magat
 */
@Local
public interface LoyerFacadeLocal {

    void create(Loyer loyer);

    void edit(Loyer loyer);

    void remove(Loyer loyer);

    Loyer find(Object id);

    List<Loyer> findAll();

    List<Loyer> findRange(int[] range);

    int count();
    
    public float getMontantDu(int id);
    
    public List<Loyer> listeByClient(int idClent);
    
}
