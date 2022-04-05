package com.mockproject.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.constant.SessionConstant;
import com.mockproject.dto.CartDto;
import com.mockproject.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
	
	@Autowired
	private CartService cartService;
	
	// localhost:8080/api/cart/update?productId={...}&quantity={...}&isReplace={...}
	
	@GetMapping("/update")
	public ResponseEntity<?> doGetUpdate(@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace") Boolean isReplace,
			HttpSession session) {
		
		CartDto currentCart = (CartDto) session.getAttribute(SessionConstant.CURRENT_CART);
		cartService.updateCart(currentCart, productId, quantity, isReplace);
		session.setAttribute(SessionConstant.CURRENT_CART, currentCart);
		
		return ResponseEntity.ok(currentCart);
	}
}
