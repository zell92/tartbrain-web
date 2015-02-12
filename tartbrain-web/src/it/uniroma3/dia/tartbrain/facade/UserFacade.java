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

	
	public User createUser(String email, String password, String username, Role role){
		

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		user.setUsername(username);
		entityManager.persist(user);
		return user;
	}


	public User loginUser(String username, String password){
		Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:userUsername AND u.password=:userPassword");
		  q.setParameter("userUsername", username);
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


	public void updateUser(User user) {
        entityManager.merge(user);
	}
	
    private void deleteUser(User user) {
        entityManager.remove(user);
    }

	public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        deleteUser(user);
	}

		


}
