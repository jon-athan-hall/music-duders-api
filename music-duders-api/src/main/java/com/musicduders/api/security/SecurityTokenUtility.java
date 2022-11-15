package com.musicduders.api.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.musicduders.api.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

@Component
public class SecurityTokenUtility {

	@Autowired
	private HttpServletRequest httpRequest;
	
	@Value("${spring.application.name}")
	private String issuer;
	
	private final Long expiration = (long) 1000000000;
	
	// TODO Make this a real secret key and hide it properly.
	private final String secretKey = "MAKETHISREAL";
	
	public String extractUsername(String token) {
		return (String) extractAllClaims(token).get("username");
	}
	
	public LocalDateTime extractExpirationTimestamp(String token) {
		return extractClaim(token, Claims::getExpiration).toInstant().atZone(ZoneId.systemDefault())
				.withZoneSameInstant(ZoneId.of("+00:00")).toLocalDateTime();
	}
	
	public String generateAccessToken(User user) {
		Claims claims = new DefaultClaims();
		claims.put("username", user.getUsername());
		return createToken(claims, user.getUsername());
	}
	
	public String generateRefreshToken(User user) {
		Claims claims = new DefaultClaims();
		claims.put("username", user.getUsername());
		return createToken(claims, user.getUsername());
	}
	
	public Boolean isTokenValid(String token, UserDetails user) {
		String username = extractClaim(token, Claims::getSubject);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}
	
	public Boolean isTokenExpired(String token) {
		Date tokenExpirationDate = extractClaim(token, Claims::getExpiration);
		return tokenExpirationDate.before(new Date());
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.replace("Bearer ", "")).getBody();
	}
	
	private String createToken(Claims claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.setId(UUID.randomUUID().toString())
				.setAudience(httpRequest.getRemoteHost())
				.setIssuer(issuer)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
}
