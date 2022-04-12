package com.mockproject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	@Override
	public List<Users> findAll() {
		return repo.findByIsDeleted(Boolean.FALSE);
	}

	@Override
	@Transactional
	public void deleteLogical(String username) {
		repo.deleteLogical(username);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void update(Users user) {
		if (StringUtils.isEmpty(user.getHashPassword())) {
			repo.updateNonPass(user.getEmail(), user.getFullname(), user.getUsername());
		} else {
			String hashPassword = bcrypt.encode(user.getHashPassword());
			user.setHashPassword(hashPassword);
			repo.update(user.getEmail(), user.getHashPassword(), user.getFullname(), user.getUsername());
		}
	}

	@Override
	public Users findByUsername(String username) {
		return repo.findByUsername(username);
	}

	
	
}
