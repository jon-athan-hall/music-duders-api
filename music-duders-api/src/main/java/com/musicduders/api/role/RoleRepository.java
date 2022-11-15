package com.musicduders.api.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
	
	// TODO Same here, is Spring smart enough?!
	Role findRoleByName(String name);

}
