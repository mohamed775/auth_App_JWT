package com.globel.system.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.globel.system.repository.UserRepo;
import com.globel.system.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


	private final UserRepo userRepo ;

	@Override
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUserName(String username) {
				return userRepo.findByEmail(username)
						.orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
			}
		};
	}
	
	
	
}
