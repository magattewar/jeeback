/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import model.Creditbailleur;

/**
 *
 * @author magat
 */
@Local
public interface CreditbailleurFacadeLocal {

    void create(Creditbailleur creditbailleur);

    void edit(Creditbailleur creditbailleur);

    void remove(Creditbailleur creditbailleur);

    Creditbailleur find(Object id);

    List<Creditbailleur> findAll();

    List<Creditbailleur> findRange(int[] range);

    int count();
    
    public List<Creditbailleur> findByBien(Integer id);
    
}
