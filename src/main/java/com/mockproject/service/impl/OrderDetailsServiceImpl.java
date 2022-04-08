package com.mockproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.dto.CartDetailDto;
import com.mockproject.repository.OrderDetailsRepo;
import com.mockproject.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	@Autowired
	public OrderDetailsRepo repo;
	
	@Override
	public void insert(CartDetailDto dto) {
		repo.insert(dto);
		
	}

}
