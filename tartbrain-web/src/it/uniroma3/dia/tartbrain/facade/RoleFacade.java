package it.uniroma3.dia.tartbrain.facade;

import it.uniroma3.dia.tartbrain.model.Role;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserFacade
 */
@Stateless
@LocalBean
public class RoleFacade {
	 @PersistenceContext(unitName = "tartbrain-web")
	    private EntityManager entityManager;

	 public Role createRole(String type){
		 Role role = new Role();
		 role.setType(type);
		 entityManager.persist(role);
		 return role;
		 
	 }
	 


}
