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
import model.Bailleur;

/**
 *
 * @author magat
 */
@Stateless
public class BailleurFacade extends AbstractFacade<Bailleur> implements BailleurFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ProjetJEEBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BailleurFacade() {
        super(Bailleur.class);
    }
    
     @Override
    public Bailleur finByCni(int parameter) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            return em.createQuery("SELECT ba FROM Bailleur ba WHERE ba.cni=:cni", Bailleur.class)
                .setParameter("cni", parameter)
                .getSingleResult();
         } catch (Exception e) {
             return null;
         }
        
    }

    @Override
    public List<Float> credit(int idbailleur) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return em.createQuery("SELECT cb.montant FROM Creditbailleur cb JOIN cb.idbien mid JOIN mid.idbailleur midb WHERE cb.idbien=:id")
        // AND mid.idbailleur. = midb.id AND cb.idbien=mid.idbailleur
        //return em.createQuery("SELECT cb.montant FROM Creditbailleur cb JOIN cb.idbien mid JOIN mid.idbailleur midb WHERE midb.id=:id")
        //return em.createQuery("SELECT cb.montant FROM Creditbailleur cb JOIN cb.idbien mid WHERE mid.idbailleur.id=:id")
        //return em.createQuery("SELECT cb.montant FROM Creditbailleur cb WHERE cb.idbien.id IN (SELECT b.id FROM Bien b WHERE b.idbailleur.id=1)")
                  //.setParameter("id", idbailleur)
          //return em.createQuery("SELECT cb.montant FROM Creditbailleur cb JOIN cb.idbien idb WHERE idb.id=1", Float.class)
          return em.createQuery("SELECT cb.montant FROM Creditbailleur cb JOIN cb.idbien mid JOIN mid.idbailleur midb WHERE midb.id=:id AND cb.paye=false", Float.class)
                  .setParameter("id", idbailleur)
                  .getResultList();
    }

    @Override
    public boolean regler(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            em.createQuery("UPDATE Creditbailleur cb SET cb.paye=true WHERE cb.idbien=:id")
                  .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
}
