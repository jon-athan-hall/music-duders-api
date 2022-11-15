package com.musicduders.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class SecurityController {
	
	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/token")
	public SecurityTokenResponse postToken(@RequestBody SecurityTokenRequest securityTokenRequest) {
		return securityService.login(securityTokenRequest);
	}
	
	@PutMapping("/refresh")
	public SecurityTokenResponse putRefresh(@RequestBody SecurityRefreshRequest securityRefreshRequest) {
		return securityService.refresh(securityRefreshRequest);
	}

}
