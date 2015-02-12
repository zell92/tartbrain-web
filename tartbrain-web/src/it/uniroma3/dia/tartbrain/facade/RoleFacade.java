package it.uniroma3.dia.tartbrain.facade;

import java.util.List;

import it.uniroma3.dia.tartbrain.model.Role;


import it.uniroma3.dia.tartbrain.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

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
	 
	 public List<Role> getAllRole(){
			CriteriaQuery<Role> cq = entityManager.getCriteriaBuilder().createQuery(Role.class);
	        cq.select(cq.from(Role.class));
	        List<Role> roles = entityManager.createQuery(cq).getResultList();
			return roles;

		}
	 


}
