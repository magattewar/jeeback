/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Creditbailleur;

/**
 *
 * @author magat
 */
@Stateless
public class CreditbailleurFacade extends AbstractFacade<Creditbailleur> implements CreditbailleurFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ProjetJEEBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditbailleurFacade() {
        super(Creditbailleur.class);
    }
    
    @Override
    public List<Creditbailleur> findByBien(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createQuery("SELECT b FROM Creditbailleur b JOIN b.idbien bi WHERE bi.id=:id", Creditbailleur.class)
                  .setParameter("id", id)
                  .getResultList();
    }
    
}
