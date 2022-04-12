package com.mockproject.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mockproject.entity.Users;
import com.mockproject.service.UsersService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	@Autowired
	private UsersService userService;
	
	@GetMapping("")
	public String doGetIndex(Model model) {
		List<Users> users = userService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("userRequest", new Users());
		return "admin/user";
	}
	
	// /admin/user/delete?username=...
	@GetMapping("/delete")
	public String doGetDelete(@RequestParam("username") String username,
			RedirectAttributes redirectAttribute) {
		try {
			userService.deleteLogical(username);
			redirectAttribute.addFlashAttribute("succeedMessage", "User " + username + " has been deleted");
		} catch (Exception ex) {
			ex.printStackTrace();
			redirectAttribute.addFlashAttribute("errorMessage", "Cannot delete user: " + username);
		}
		return "redirect:/admin/user";
	}
	
	// /admin/user/edit?username=...
	@GetMapping("/edit")
	public String doGetEditUser(@RequestParam("username") String username, Model model) {
		Users userRequest = userService.findByUsername(username);
		model.addAttribute("userRequest", userRequest);
		return "admin/user::#form";
	}
	
	
	// /admin/user/create
	@PostMapping("/create")
	public String doPostCreateUser(@Valid @ModelAttribute("userRequest") Users userRequest,
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		String errorMessage = null;
		try {
			if (bindingResult.hasErrors()) {
				errorMessage = "User is not valid";
				redirectAttribute.addFlashAttribute("errorMessage", errorMessage);
			} else {
				userService.save(userRequest);
				redirectAttribute.addFlashAttribute("succeedMessage", "User " + userRequest.getUsername() + " has been created");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			errorMessage = "Cannot create user!";
			redirectAttribute.addFlashAttribute("errorMessage", errorMessage);
		}
		return "redirect:/admin/user";
	}
	
	// /admin/user/edit
	@PostMapping("/edit")
	public String doPostEditUser(@Valid @ModelAttribute("userRequest") Users userRequest,
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		String errorMessage = null;
		try {
			if (bindingResult.hasErrors()) {
				errorMessage = "User is not valid";
				redirectAttribute.addFlashAttribute("errorMessage", errorMessage);
			} else {
				userService.update(userRequest);
				redirectAttribute.addFlashAttribute("succeedMessage", "User " + userRequest.getUsername() + " has been edited");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			errorMessage = "Cannot edit user!";
			redirectAttribute.addFlashAttribute("errorMessage", errorMessage);
		}
		return "redirect:/admin/user";
	}
}
