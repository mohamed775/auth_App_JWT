package com.globel.system.service.impl;

import java.util.HashMap;

import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.Registration.Signing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.globel.system.dto.JWTAuthenticatedResponse;
import com.globel.system.dto.SignUpRequest;
import com.globel.system.dto.SigninRequest;
import com.globel.system.entity.Role;
import com.globel.system.entity.User;
import com.globel.system.repository.UserRepo;
import com.globel.system.service.AuthenticationService;
import com.globel.system.service.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepo userRepo ;
	
	private final PasswordEncoder passwordEncoder ;
	
	private final AuthenticationManager authenticationManager ;
	
	private final JWTService jWTService ;
	
	private final JWTAuthenticatedResponse jWTAuthenticatedResponse ;

	
	
	
	public User singUp(SignUpRequest signUpRequest) {
		User user = new User();
		
		user.setEmail(signUpRequest.getEmail());
		user.setFirstName(signUpRequest.getFirstName());
		user.setSecondName(signUpRequest.getLastName());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setRole(Role.USER);
		
		userRepo.save(user);
		return user ;
	}
	
	public  JWTAuthenticatedResponse signin(SigninRequest signing ) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signing.getEmail(), signing.getPassword()));
		
		var user = userRepo.findByEmail(signing.getEmail()).orElseThrow(()-> new IllegalArgumentException("invaild email or password"));
	    var jwt = jWTService.generateToken(user) ;
	    var refreshToken = jWTService.generateRefreshToken(new HashMap<>(), user) ;
	    
	    jWTAuthenticatedResponse.setToken(jwt);
	    jWTAuthenticatedResponse.setRefreshToken(refreshToken);
	    
	    return  jWTAuthenticatedResponse ;
	}
}
