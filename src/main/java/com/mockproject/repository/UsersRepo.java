package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{

	// SELECT * FROM users WHERE username = ?
	Users findByUsername(String username);
	
}
