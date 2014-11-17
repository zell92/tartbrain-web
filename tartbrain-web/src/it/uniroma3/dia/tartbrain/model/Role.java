package it.uniroma3.dia.tartbrain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "User" database table.
 * 
 */
@Entity
@Table(name="\"Role\"")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (nullable=false)
	private String type;

	

	public Role() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


}