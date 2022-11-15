package com.musicduders.api.security;

import org.springframework.stereotype.Component;

@Component
public class SecurityRefreshRequest {

	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
