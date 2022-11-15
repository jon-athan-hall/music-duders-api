package com.musicduders.api.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.musicduders.api.role.Role;

public interface UserService {
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public User createUser(UserRequest userRequest);
	
	public Role createRole(Role role);
	
	void addRoleToUser(String userId, Role role);
	
	public User getUser(String username);
	
	// TODO Load only a page of users, not every single user in the DB.
	public List<User> getAllUsers();
	
}
