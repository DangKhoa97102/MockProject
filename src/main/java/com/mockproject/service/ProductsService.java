package com.mockproject.service;

import java.util.List;

import com.mockproject.entity.Products;

public interface ProductsService {
	
	List<Products> findAll();
	
	Products findById(Long id);
	
	void updateQuantity(Integer newQuantity, Long productId);
}
