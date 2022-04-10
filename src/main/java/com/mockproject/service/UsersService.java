package com.mockproject.service;

import com.mockproject.entity.Users;

public interface UsersService {

	Users doLogin(String username, String password);
	
	Users save(Users user);
}
