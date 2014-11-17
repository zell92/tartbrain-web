package it.uniroma3.dia.tartbrain.facade;

import java.util.List;

import it.uniroma3.dia.tartbrain.model.Role;
import it.uniroma3.dia.tartbrain.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserFacade
 */
@Stateless
@LocalBean
public class UserFacade {
	@PersistenceContext(unitName = "tartbrain-web")
	private EntityManager entityManager;

	public User createUser(String email, String password, Role role){
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		entityManager.persist(user);
		return user;
	}

	public User loginUser(String email, String password){
		List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.email LIKE :userEmail AND u.password LIKE :userPassword").setParameter("userEmail", email).setParameter("userPassword", password).getResultList();
		if(users.size() == 0) 
			return null;
		else
			return users.get(0);
	}



}
