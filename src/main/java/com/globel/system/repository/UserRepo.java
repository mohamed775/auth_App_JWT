package com.globel.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globel.system.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long>  {

	Optional<User> findByEmail(String email);
	
	
}
