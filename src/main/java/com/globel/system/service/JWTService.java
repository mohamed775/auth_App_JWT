package com.globel.system.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

	String generateToken(UserDetails userDetails);
	
	String extractuserName(String token);
	
	String generateRefreshToken(Map<String , Object> extraClaims , UserDetails userDetails);
	
	boolean isTokenValid (String token , UserDetails userDetails);
	
}
