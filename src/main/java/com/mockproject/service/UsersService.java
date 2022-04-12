package com.mockproject.service;

import java.util.List;

import com.mockproject.entity.Users;

public interface UsersService {
	Users doLogin(String username, String password);
	Users findByUsername(String username);
	Users save(Users user);
	List<Users> findAll();
	void deleteLogical(String username);
	void update(Users user);
}
