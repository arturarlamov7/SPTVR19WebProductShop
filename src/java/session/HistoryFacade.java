/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.History;
import entity.Person;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author artur
 */
@Stateless
public class HistoryFacade extends AbstractFacade<History> {

    @PersistenceContext(unitName = "SPTVR19ArlamovProductShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }

    public List<Product> findBuyProduct(Person person) {
        try {
            List<Product> listBuyProduct = em.createQuery("SELECT h.product FROM History h WHERE h.person = :person AND h.takeOnDate=null")
                    .setParameter("person", person)
                    .getResultList();
            return listBuyProduct;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
}
