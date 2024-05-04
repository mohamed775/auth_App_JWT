package com.globel.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.globel.system.entity.Role;
import com.globel.system.entity.User;
import com.globel.system.repository.UserRepo;

@SpringBootApplication
public class AuthAppApplication implements CommandLineRunner{

	@Autowired
	private UserRepo userRepo ;
	public static void main(String[] args) {
		SpringApplication.run(AuthAppApplication.class, args);
	}
	
	public void run(String... args) {
		User adminAccount = userRepo.findByRole(Role.ADMIN);
		if(null == adminAccount) {
			User user = new User();
			
			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setSecondName("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepo.save(user);
			
		}
	}

}
