package com.musicduders.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicduders.api.role.Role;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	UserService userService;
	
	// TODO Consider ResponseEntity here.
	@GetMapping
	public List<User> getUsers() {
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") String id) {
		User user = userService.getUser(id);
		return user;
	}
	
	// TODO Add appropriate URI with 201 status code.
	@PostMapping
	public User postUser(@RequestBody UserRequest user) {
		return userService.createUser(user);
	}
	
	@PostMapping("/roles")
	public Role postRole(@RequestBody Role role) {
		return userService.createRole(role);
	}
	
	@PostMapping("/{id}/roles")
	public void addRoleToUser(@PathVariable("id") String id, @RequestBody Role role) {
		userService.addRoleToUser(id, role);
	}
	
}
