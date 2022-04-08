package com.mockproject.service;

import com.mockproject.dto.CartDto;
import com.mockproject.entity.Users;

public interface CartService {
	
	CartDto updateCart(CartDto cart, Long productId, Integer quantity, Boolean isReplace);
	Integer getTotalQuantity(CartDto cart);
	Double getTotalPrice(CartDto cart);
	void insert(CartDto cart, Users user, String address, String phone) throws Exception;
}
