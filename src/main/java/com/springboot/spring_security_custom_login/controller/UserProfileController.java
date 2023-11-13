package com.springboot.spring_security_custom_login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.spring_security_custom_login.dto.UserData;
import com.springboot.spring_security_custom_login.service.UserDataService;

@Controller
public class UserProfileController {
	
	private final UserDataService userDataService;
	
	public UserProfileController(UserDataService userDataService) {
		this.userDataService=userDataService;
	}

	@GetMapping("/user/profile")
	public String viewProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			UserData userData = userDataService.getUserData(username);
			model.addAttribute("username", userData.getUsername());
			model.addAttribute("email", userData.getEmail());
			model.addAttribute("userRole", userData.getRoleName());
			return "profile";
		}
		return "profile";
	}
}
