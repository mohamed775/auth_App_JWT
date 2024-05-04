package com.globel.system.service;

import com.globel.system.dto.SignUpRequest;
import com.globel.system.entity.User;

public interface AuthenticationService {

	 User singUp(SignUpRequest signUpRequest) ;

	
}
