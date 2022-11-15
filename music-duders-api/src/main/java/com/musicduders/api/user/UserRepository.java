package com.musicduders.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT u from User WHERE u.username = ?1")
	User findUserByUsername(String username);

}
