package com.musicduders.api.user;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.musicduders.api.role.Role;

/**
 * TODO Consider implements UserDetails to reuse common method of a Principal object.
 * 
 * @author Jonathan Hall
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(length = 36)
	private String id = UUID.randomUUID().toString();
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	/**
	 * Every time a User is loaded, all of the roles will be loaded too.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
