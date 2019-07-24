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
import model.Bien;

/**
 *
 * @author magat
 */
@Stateless
public class BienFacade extends AbstractFacade<Bien> implements BienFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ProjetJEEBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BienFacade() {
        super(Bien.class);
    }
    
    @Override
    public void updateOccupation(int id,boolean etat) {
        Bien bien = em.find(Bien.class, id);
        em.getTransaction().begin();
        bien.setDisponibilite(etat);
        em.getTransaction().commit();
    }

    @Override
    public List<Bien> listeByBailleur(int idBailleur) {
        return em.createQuery("SELECT b FROM Bien b JOIN b.idbailleur bail WHERE bail.id=:id", Bien.class)
                  .setParameter("id", idBailleur)
                  .getResultList();
    }

    @Override
    public void changerDispo(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Bien bien = em.find(Bien.class, id);
        em.getTransaction().begin();
        bien.setDisponibilite(true);
        em.getTransaction().commit();
    }
    
}
