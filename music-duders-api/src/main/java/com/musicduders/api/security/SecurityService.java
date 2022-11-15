package com.musicduders.api.security;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.musicduders.api.user.User;
import com.musicduders.api.user.UserRepository;

@Service
public class SecurityService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SecurityTokenUtility securityTokenUtility;
	
	public SecurityTokenResponse login(SecurityTokenRequest securityTokenRequest) {
		User user = userRepository.findUserByUsername(securityTokenRequest.getUsername());
		
		if (!passwordEncoder.matches(securityTokenRequest.getPassword(), user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid login credentials.");
		}
		
		String accessToken = securityTokenUtility.generateAccessToken(user);
		String refreshToken = securityTokenUtility.generateRefreshToken(user);
		LocalDateTime expirationTimestamp = securityTokenUtility.extractExpirationTimestamp(accessToken);
		
		SecurityTokenResponse securityTokenResponse = new SecurityTokenResponse();

		securityTokenResponse.setAccessToken(accessToken);
		securityTokenResponse.setRefreshToken(refreshToken);
		securityTokenResponse.setExpirationTimestamp(expirationTimestamp);
		
		return securityTokenResponse;		
	}
	
	public SecurityTokenResponse refresh(SecurityRefreshRequest securityRefreshRequest) {
		if (securityTokenUtility.isTokenExpired(securityRefreshRequest.getToken())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expired.");
		}
		
		String username = securityTokenUtility.extractUsername(securityRefreshRequest.getToken());
		User user = userRepository.findUserByUsername(username);
		
		String accessToken = securityTokenUtility.generateAccessToken(user);
		LocalDateTime expirationTimestamp = securityTokenUtility.extractExpirationTimestamp(accessToken);
		
		SecurityTokenResponse securityTokenResponse = new SecurityTokenResponse();
		
		securityTokenResponse.setAccessToken(accessToken);
		securityTokenResponse.setExpirationTimestamp(expirationTimestamp);
		
		return securityTokenResponse;
	}

}
