/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author artur
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> {

    @PersistenceContext(unitName = "SPTVR19ArlamovProductShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }

    public Role findByName(String roleName) {
        try {
            return (Role) em.createQuery("SELECT role FROM Role role WHERE role.roleName = :roleName")
                    .setParameter("roleName", roleName)
                    .getSingleResult();
        } catch (Exception c) {
            return null;
        }
        
    }
    
}
