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
import model.Client;

/**
 *
 * @author magat
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ProjetJEEBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    @Override
    public List<Client> getByCni(String cni) {
      return em.createQuery("SELECT cl FROM Client cl WHERE cl.cni=:cni", Client.class)
                .setParameter("cni", cni)
                .getResultList();
    }
    
}
