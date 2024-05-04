package com.globel.system.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.globel.system.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {

	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 ))
				.signWith(getSignKey() , SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	public String extractuserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	private <T> T extractClaim(String token , Function<Claims ,T> claimResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimResolvers.apply(claims);
	}
	
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
				
				
	}
	
	private Key getSignKey() {
		byte[] key = Decoders.BASE64.decode("Gp4lyY4MyEd0wqRWMuuWfcgUO3X8ZbKz3TcbOZCtR_Xjv8Aa-jjP8mN4c6XZQuUv\r\n"
				+ "");
		return Keys.hmacShaKeyFor(key);
	}
	
	public boolean isTokenValid (String token , UserDetails userDetails) {
		final String username = extractuserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
	
	
}
