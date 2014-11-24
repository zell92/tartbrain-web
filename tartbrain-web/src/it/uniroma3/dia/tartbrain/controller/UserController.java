package it.uniroma3.dia.tartbrain.controller;


import it.uniroma3.dia.tartbrain.facade.RoleFacade;
import it.uniroma3.dia.tartbrain.facade.UserFacade;
import it.uniroma3.dia.tartbrain.model.Role;
import it.uniroma3.dia.tartbrain.model.User;
import it.uniroma3.dia.tartbrain.util.MyLog4J;

import java.util.List;










import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@ManagedBean
@SessionScoped
public class UserController {
	
	private String email;
	private String password;
	private Long id;
	private User user;
	private Role role;
	private String roleType;
	
	private List<User> users;
	
	@EJB
	private UserFacade userFacade;
	@EJB
	private RoleFacade roleFacade;
	@EJB
	private MyLog4J myLog4J;

	public String createUser() {
		myLog4J.getLog().debug("creazione");
		this.user = userFacade.createUser(email, password,null);
		//this.id = user.getId();
		return "indexA"; 
	}
	
	public String loginUser() {

	      //writing some logs at different levels
		myLog4J.getLog().debug("login");
	      //log.debug("Test Livello DEBUG");
	      //log.info("Test Livello INFO");
	      //log.warn("Test Livello WARNING");
	      //log.error("Test Livello ERROR");
	      //log.fatal("Test Livello FATAL");
		
		this.user = userFacade.loginUser(email, password);
		if(this.user == null)
			return "error";
		else
			if(this.user.getRole().getType().equals("Admin"))
			return "indexA";
			else
				return "indexU";
	}
	
	//lista utenti
	public String usersList(){
		this.users=userFacade.getAllUser();
		return "userList";
	}
	
	public String findUser(Long id){
		this.user= userFacade.getUser(id);
		return "user";
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
