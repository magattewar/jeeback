/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import model.Moyenpaiement;

/**
 *
 * @author magat
 */
@Local
public interface MoyenpaiementFacadeLocal {

    void create(Moyenpaiement moyenpaiement);

    void edit(Moyenpaiement moyenpaiement);

    void remove(Moyenpaiement moyenpaiement);

    Moyenpaiement find(Object id);

    List<Moyenpaiement> findAll();

    List<Moyenpaiement> findRange(int[] range);

    int count();
    
}
