package com.mockproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Users;
import com.mockproject.repository.UsersRepo;
import com.mockproject.service.ProductsService;
import com.mockproject.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo repo;
	
	//Test
	//private ProductsService productsService;
	
	@Override
	public Users doLogin(String username, String password) {
		// TODO Auto-generated method stub
		Users user = repo.findByUsername(username);
		
		if(user != null) {
			//check password
			String hashPassword = user.getHashPassword();
			boolean checkPassword = bcrypt.matches(password, hashPassword);
			return checkPassword == true ? user : null;
		} else {
			return null;	
		}
		
	}

	
	
}
