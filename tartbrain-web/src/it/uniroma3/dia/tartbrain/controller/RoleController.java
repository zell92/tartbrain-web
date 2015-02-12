package it.uniroma3.dia.tartbrain.controller;


import it.uniroma3.dia.tartbrain.facade.RoleFacade;
import it.uniroma3.dia.tartbrain.model.Role;




import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class RoleController {
	
	@ManagedProperty(value="")
	private String type;
	private Role role;
	private Long id;
		
	@EJB
	private RoleFacade roleFacade;

	public String createRole() {
		this.role = roleFacade.createRole(type);
		return "index"; 
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	


}