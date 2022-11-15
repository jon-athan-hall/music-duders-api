package com.musicduders.api.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.musicduders.api.role.Role;
import com.musicduders.api.role.RoleRepository;

/**
 * TODO Look into Lombok for @RequiredArgsConstructor annotation,
 * for dependency injection.
 * @author Jonathan
 *
 */
@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * This is a method from UserDetailsService interface that needs to be implemented
	 * so Spring Security knows how to load in a User, in this case, from the database.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username); // TODO Check for not found.
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		/**
		 * Spring Security also needs authorities to be in the correct form, in this case,
		 * a collection of SimpleGrantedAuthority instances, a subclass of GrantedAuthority.
		 */
		user.getRoles().forEach(role -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		/**
		 Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		*/
		
		/**
		 * Use full qualified name for this class to avoid confusion with the User class
		 * in the project. This other User class is what Spring Security needs.
		 */
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
	
	// TODO Load only a page of users, not every single user in the DB.
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User getUser(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public User createUser(UserRequest userRequest) {
		userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		User user = userMapper.toUser(userRequest);
		return userRepository.save(user);
	}
	
	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}
	
	@Override
	public void addRoleToUser(String userId, Role role) {
		User user = (userRepository.findById(userId)).get();
		Role foundRole = roleRepository.findRoleByName(role.getName());
		
		// TODO Consider @Transactional annotation for this class.
		user.getRoles().add(foundRole);
		userRepository.save(user);
	}
	
}
