/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import model.Bien;

/**
 *
 * @author magat
 */
@Local
public interface BienFacadeLocal {

    void create(Bien bien);

    void edit(Bien bien);

    void remove(Bien bien);

    Bien find(Object id);

    List<Bien> findAll();

    List<Bien> findRange(int[] range);

    int count();
    
    void updateOccupation(int id,boolean etat);

    public List<Bien> listeByBailleur(int idBailleur);

    public void changerDispo(Integer id);
    
}
