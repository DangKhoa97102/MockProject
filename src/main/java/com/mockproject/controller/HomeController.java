package com.mockproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mockproject.entity.Products;
import com.mockproject.entity.Users;
import com.mockproject.service.ProductsService;
import com.mockproject.service.UsersService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ProductsService productService;

	@Autowired
	private UsersService usersService;

	// localhost:8080/index
	@GetMapping("index")
	public String doGetIndex(Model model) {
		List<Products> products = productService.findAll();
		model.addAttribute("products", products);
		return "user/index";
	}
	
	//Login
	@GetMapping("login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user/login";
	}
	
	//Logout
	@GetMapping("logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute("currentUser");
		return "redirect:/index";
	}
	
	//Register
	@GetMapping("register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRegister", new Users());
		return "user/register";
	}
	
	

	// forward != redirect ( chuyển hướng và chuyển tiếp )
	@PostMapping("login")
	public String doPostLogin(@ModelAttribute("userRequest") Users userRequest, HttpSession session) {
		Users userResponse = usersService.doLogin(userRequest.getUsername(), userRequest.getHashPassword());
		if(userResponse != null) {
			session.setAttribute("currentUser", userResponse);
			return "redirect:/index";
		} else {
			return "redirect:/login";
		}
	}
	
	//Post Mapping Register
	@PostMapping("register")
	public String doPostRegister(@ModelAttribute Users userRegister) {
		Users userResponse = usersService.save(userRegister);
		if(userResponse != null) {
			return "redirect:/index";
		} else {
			return "redirect:/register";
		}
		
		
	}
}
