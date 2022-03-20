package com.mockproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mockproject.entity.Products;
import com.mockproject.service.ProductsService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ProductsService productService;
	
	// localhost:8080/index
	@GetMapping("index")
	public String doGetIndex(Model model) {
		List<Products> products = productService.findAll();
		model.addAttribute("products", products);
		return "user/index";
	}
}
