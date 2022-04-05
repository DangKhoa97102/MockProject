package com.mockproject.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.dto.CartDetailDto;
import com.mockproject.dto.CartDto;
import com.mockproject.entity.Products;
import com.mockproject.service.CartService;
import com.mockproject.service.ProductsService;

@Service
public class CartServiceImpl implements CartService {

	// Lấy thông tin từ bảng products
	@Autowired
	private ProductsService productsService;

	@Override
	public CartDto updateCart(CartDto cart, Long productId, Integer quantity, Boolean isReplace) {
		
		Products product = productsService.findById(productId);
		
		HashMap<Long , CartDetailDto> listDetail = cart.getListDetail();
		
		// Trường hợp
		// 1- Thêm mới sản phẩm
		// 2- Update sản phẩm: 2.1- cộng dồn sản phẩm || 2.2- Thay thế sản phẩm (replace) 
		// 3- Delete sản phẩm (remove) = Update 2.2 với quantity = 0
		
		if (!listDetail.containsKey(productId)) {
			// Thêm mới
			CartDetailDto cartDetail = addNewCartDetail(product, quantity);
			listDetail.put(productId, cartDetail); // save to Map :3			
		} else if(quantity > 0) {
			// Update
			if (isReplace) {
				// Thay thế số lượng cũ = số lượng mới.
				listDetail.get(productId).setQuantity(quantity);
			} else {
				// Cộng dồn.
				Integer oldQuantity = listDetail.get(productId).getQuantity();
				Integer newQuantity = oldQuantity + quantity;
				listDetail.get(productId).setQuantity(newQuantity);
			}
		} else {
			// Delete
			listDetail.remove(productId);
			}
		cart.setTotalQuantity(getTotalQuantity(cart));
		cart.setTotalPrice(getTotalPrice(cart));
		return cart;
	}

	@Override
	public Integer getTotalQuantity(CartDto cart) {
		Integer totalQuantity = 0;
		HashMap<Long, CartDetailDto> listDetail = cart.getListDetail();
		for (CartDetailDto cartDetail : listDetail.values()) {
			totalQuantity += cartDetail.getQuantity();
		}
		return totalQuantity;
	}

	@Override
	public Double getTotalPrice(CartDto cart) {
		Double totalPrice = 0D;
		HashMap<Long, CartDetailDto> listDetail = cart.getListDetail();
		for (CartDetailDto cartDetail : listDetail.values()) {
			totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
		}
		return totalPrice;
	}
	
	private CartDetailDto addNewCartDetail(Products product, Integer quantity) {
		CartDetailDto cartDetail = new CartDetailDto();
		cartDetail.setProductId(product.getId());
		cartDetail.setQuantity(quantity);
		cartDetail.setPrice(product.getPrice());
		cartDetail.setName(product.getName());
		cartDetail.setImgUrl(product.getImgUrl());
		cartDetail.setSlug(product.getSlug());
		return cartDetail;
	}

}
