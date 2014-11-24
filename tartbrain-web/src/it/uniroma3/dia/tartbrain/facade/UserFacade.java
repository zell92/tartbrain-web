package it.uniroma3.dia.tartbrain.facade;

import java.util.List;

import it.uniroma3.dia.tartbrain.model.Role;
import it.uniroma3.dia.tartbrain.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;



/**
 * Session Bean implementation class UserFacade
 */
@Stateless
public class UserFacade {
	@PersistenceContext (unitName = "tartbrain-web")
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
		System.out.println("inizio: "+email+ " " +password);
		Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.email=:userEmail AND u.password=:userPassword");
		  q.setParameter("userEmail", email);
		  q.setParameter("userPassword", password);
		List<User> users = q.getResultList();
		System.out.println(users.size());
		if(users.size() == 0) 
			return null;
		else
			return users.get(0);
		
	}

	public List<User> getAllUser(){
		CriteriaQuery<User> cq = entityManager.getCriteriaBuilder().createQuery(User.class);
        cq.select(cq.from(User.class));
        List<User> users = entityManager.createQuery(cq).getResultList();
		return users;

	} 
	
	public User getUser(Long id){
		 User user = entityManager.find(User.class, id);
			return user;

	}


}
