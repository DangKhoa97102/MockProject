package com.mockproject.service;

import org.springframework.stereotype.Service;

import com.mockproject.entity.Users;

public interface UsersService {

	Users doLogin(String username, String password);
	
	Users save(Users user);
}
