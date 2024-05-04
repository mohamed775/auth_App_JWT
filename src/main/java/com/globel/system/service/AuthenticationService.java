package com.globel.system.service;

import com.globel.system.dto.JWTAuthenticatedResponse;
import com.globel.system.dto.RefreshToken;
import com.globel.system.dto.SignUpRequest;
import com.globel.system.dto.SigninRequest;
import com.globel.system.entity.User;

public interface AuthenticationService {

	 User singUp(SignUpRequest signUpRequest) ;

	 JWTAuthenticatedResponse signin(SigninRequest signing );
	 
	 JWTAuthenticatedResponse refreshToken(RefreshToken refreshToken);

	
}
