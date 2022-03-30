package com.mockproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Roles;
import com.mockproject.entity.Users;
import com.mockproject.repository.UsersRepo;
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
		// 
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

	@Override
	@Transactional
	public Users save(Users user) {
		//
		String hashPassword = bcrypt.encode(user.getHashPassword());
		user.setHashPassword(hashPassword);
		// set Role vi user chua co role, va Role khong co trong bang dang ky.
		user.setRole(new Roles(2L, "user"));
		// user mới tạo thì isDeleted phải là 0.
		user.setIsDeleted(Boolean.FALSE);
		return repo.saveAndFlush(user);
	}

	
	
}
