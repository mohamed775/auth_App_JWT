package com.globel.system.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.globel.system.dto.SignUpRequest;
import com.globel.system.entity.Role;
import com.globel.system.entity.User;
import com.globel.system.repository.UserRepo;
import com.globel.system.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepo userRepo ;
	
	private final PasswordEncoder passwordEncoder ;
	
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
}
