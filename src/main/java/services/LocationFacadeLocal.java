/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import model.Location;

/**
 *
 * @author magat
 */
@Local
public interface LocationFacadeLocal {

    void create(Location location);

    void edit(Location location);

    void remove(Location location);

    Location find(Object id);

    List<Location> findAll();

    List<Location> findRange(int[] range);

    int count();
    public Location findLast(Date dateenregistrement);

    public void liberer(int idLocation);
    
}
