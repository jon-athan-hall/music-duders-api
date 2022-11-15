package com.musicduders.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.musicduders.api.user.UserService;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private SecurityTokenUtility securityTokenUtility;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader != null) {
			if (authorizationHeader.startsWith("Bearer ")) {
				String token = authorizationHeader.substring(7);
				String username = securityTokenUtility.extractUsername(token);
				
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = userService.loadUserByUsername(username);
					
					if (securityTokenUtility.isTokenValid(token, userDetails)) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
								new UsernamePasswordAuthenticationToken(
										userDetails.getUsername(),
										userDetails.getPassword(),
										userDetails.getAuthorities());
						usernamePasswordAuthenticationToken.setDetails(
								new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
}
