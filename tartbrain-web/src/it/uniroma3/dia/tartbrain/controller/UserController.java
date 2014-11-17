package it.uniroma3.dia.tartbrain.controller;


import it.uniroma3.dia.tartbrain.facade.UserFacade;
import it.uniroma3.dia.tartbrain.model.Role;
import it.uniroma3.dia.tartbrain.model.User;

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
	
	@ManagedProperty(value="")
	private String email;
	private String password;
	private Long id;
	private User user;
	private Role role;
	
	private List<User> users;
	
	@EJB
	private UserFacade userFacade;

	public String createUser() {
		this.user = userFacade.createUser(email, password,role);
		this.id = user.getId();
		return "index"; 
	}
	
	public String loginUser() {
		 //load configuration File
		//System.out.println(this.getClass().getClassLoader().getResource("").getPath());
	      //PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("").getPath()+"log.properties");
	 
	      //load configuration File in XML format
	      //DomConfigurator.configure("myLog.xml");
	 
	      //get Logger Instance
	      Logger log = Logger.getLogger(UserController.class);
	 
	      //writing some logs at different levels
	      log.debug("Test Livello DEBUG");
	      log.info("Test Livello INFO");
	      log.warn("Test Livello WARNING");
	      log.error("Test Livello ERROR");
	      log.fatal("Test Livello FATAL");
		
		this.user = userFacade.loginUser(email, password);
		if(this.user == null)
			return "error";
		else
			return "index";
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
