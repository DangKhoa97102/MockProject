package com.mockproject.util;

import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;

import com.mockproject.constant.SessionConstant;
import com.mockproject.dto.CartDto;

public class SessionUtil {

	public static void validateCart(HttpSession session) {
		if (ObjectUtils.isEmpty(session.getAttribute(SessionConstant.CURRENT_CART))) {
			session.setAttribute(SessionConstant.CURRENT_CART, new CartDto());
		}
	}
	
}
