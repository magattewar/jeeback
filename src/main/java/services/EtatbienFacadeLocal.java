/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import model.Etatbien;

/**
 *
 * @author magat
 */
@Local
public interface EtatbienFacadeLocal {

    void create(Etatbien etatbien);

    void edit(Etatbien etatbien);

    void remove(Etatbien etatbien);

    Etatbien find(Object id);

    List<Etatbien> findAll();

    List<Etatbien> findRange(int[] range);

    int count();
    
}
