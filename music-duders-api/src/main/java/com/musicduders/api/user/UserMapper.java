package com.musicduders.api.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	/**
	 * Converts a UserRequest to a User.
	 * 
	 * @param artistRequest
	 * @return Artist
	 */
	public User toUser(UserRequest userRequest) {
		User user = new User();
		
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		
		return user;
	}
	
}
