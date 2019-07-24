/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Moyenpaiement;

/**
 *
 * @author magat
 */
@Stateless
public class MoyenpaiementFacade extends AbstractFacade<Moyenpaiement> implements MoyenpaiementFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ProjetJEEBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MoyenpaiementFacade() {
        super(Moyenpaiement.class);
    }
    
}
