package com.globel.system.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.globel.system.config.UsernameNotFoundException;
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
			public UserDetails loadUserByUsername(String username)
					throws org.springframework.security.core.userdetails.UsernameNotFoundException {
				// TODO Auto-generated method stub
				return userRepo.findByEmail(username)
						.orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
			}
		};
	}
	
	
	
}
