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
import model.Loyer;

/**
 *
 * @author magat
 */
@Stateless
public class LoyerFacade extends AbstractFacade<Loyer> implements LoyerFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ProjetJEEBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoyerFacade() {
        super(Loyer.class);
    }
    
    @Override
    public float getMontantDu(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Float> liste;
        liste = em.createQuery("SELECT loy.montant FROM Loyer loy JOIN loy.idlocation loc JOIN loc.idclient cli WHERE cli.id=:id AND loy.paye=false", Float.class)
                .setParameter("id", id)
                .getResultList();
        
        float somme=0;
        for (Float float1 : liste) {
            somme +=float1;
        }
        return somme;
    }
    
    @Override
    public List<Loyer> listeByClient(int idClent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createQuery("SELECT loy FROM Loyer loy JOIN loy.idlocation idl JOIN idl.idclient idc WHERE idc.id=:id", Loyer.class)
                  .setParameter("id", idClent)
                  .getResultList();
    }
    
}
