package com.springboot.spring_security_custom_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.spring_security_custom_login.dto.RegistrationForm;
import com.springboot.spring_security_custom_login.entity.Role;
import com.springboot.spring_security_custom_login.entity.User;
import com.springboot.spring_security_custom_login.service.RoleService;
import com.springboot.spring_security_custom_login.service.UserService;

@Controller
public class RegistrationController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute("RegistrationForm") RegistrationForm registrationForm, RedirectAttributes redirectAttributes) {
		if(userService.findByUsername(registrationForm.getUsername() )!= null) {
			redirectAttributes.addFlashAttribute("error_msg", "Username already exists!");
			return "redirect:/register";
		}			
		
		if(userService.findByEmail(registrationForm.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("error_msg", "Email already exists!");
			return "redirect:/register";
		}
		
		if(!registrationForm.getConfirmPassword().equals(registrationForm.getPassword())) {
			redirectAttributes.addFlashAttribute("error_msg", "Password do not match!");
			return "redirect:/register";
		}
		User user = new User();		
		user.setUsername(registrationForm.getUsername());
	    user.setEmail(registrationForm.getEmail().toLowerCase());
	    user.setPassword(registrationForm.getPassword());
	    
	    Role userRole = roleService.findByName("user");
	    if(userRole == null) {
	    	userRole = new Role("user");
	    	roleService.addRole(userRole);
	    }
	    user.setRole(userRole);
		userService.registerUser(user);
		redirectAttributes.addFlashAttribute("success_msg", "User registered successfully");
		return "redirect:/login";
	}
	
}
