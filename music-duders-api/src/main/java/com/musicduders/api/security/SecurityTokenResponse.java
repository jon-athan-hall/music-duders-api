package com.musicduders.api.security;

import java.time.LocalDateTime;

public class SecurityTokenResponse {
	
	private String accessToken;
	
	private String refreshToken;
	
	private LocalDateTime expirationTimestamp;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public LocalDateTime getExpirationTimestamp() {
		return expirationTimestamp;
	}
	
	public void setExpirationTimestamp(LocalDateTime expirationTimestamp) {
		this.expirationTimestamp = expirationTimestamp;
	}

}
