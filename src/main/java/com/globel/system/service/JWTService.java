package com.globel.system.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

	String generateToken(UserDetails userDetails);
	
	String extractuserName(String token);
	
	boolean isTokenValid (String token , UserDetails userDetails);
	
}
