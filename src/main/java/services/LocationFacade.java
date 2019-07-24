/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Location;

/**
 *
 * @author magat
 */
@Stateless
public class LocationFacade extends AbstractFacade<Location> implements LocationFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ProjetJEEBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocationFacade() {
        super(Location.class);
    }
    
    @Override
    public Location findLast(Date dateenregistrement) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createQuery("SELECT loc FROM Location loc WHERE loc.dateenregistrement=:date", Location.class)
                .setParameter("date", dateenregistrement)
                .getSingleResult();
    }

    @Override
    public void liberer(int idLocation) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Location loc = em.find(Location.class, idLocation);
        em.getTransaction().begin();
        loc.setOccupe(false);
        em.getTransaction().commit();
    }
    
}
