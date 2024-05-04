package com.globel.system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globel.system.dto.JWTAuthenticatedResponse;
import com.globel.system.dto.SignUpRequest;
import com.globel.system.dto.SigninRequest;
import com.globel.system.entity.User;
import com.globel.system.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService  authenticationService ;
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
		
		return ResponseEntity.ok(authenticationService.singUp(signUpRequest));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JWTAuthenticatedResponse> signin(@RequestBody SigninRequest SigninRequest) {
		
		return ResponseEntity.ok(authenticationService.signin(SigninRequest));
	}
}
