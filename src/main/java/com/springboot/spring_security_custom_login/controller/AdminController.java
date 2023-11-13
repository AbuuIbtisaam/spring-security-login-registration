package com.springboot.spring_security_custom_login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/admin/new_role")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String addRoles(Model model) {
		model.addAttribute("role", new Role());
		return "role";
	}

	@GetMapping("/admin/new_user")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String addNewUser(Model model) {
		List<Role> roles = roleService.getAllRoles();
		model.addAttribute("registrationForm", new RegistrationForm());
		model.addAttribute("roles", roles);
		return "add_user";
	}
	
	@PostMapping("/add_role")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String addNewRole( Role role, RedirectAttributes redirectAttributes) {
		if(roleService.findByName(role.getName())!= null) {
			redirectAttributes.addFlashAttribute("error_msg", "Role already exists!");
			return "redirect:/admin/new_role";
		}

    	roleService.addRole(role);	    
		redirectAttributes.addFlashAttribute("success_msg", "Role added successfully");
		return "redirect:/admin/new_role";
	}
	
	@PostMapping("/admin/new_user")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String registerNewUser(@ModelAttribute("RegistrationForm") RegistrationForm registrationForm, RedirectAttributes redirectAttributes) {
		if(userService.findByUsername(registrationForm.getUsername() )!= null) {
			redirectAttributes.addFlashAttribute("error_msg", "Username already exists!");
			return "redirect:/admin/new_user";
		}			
		
		if(userService.findByEmail(registrationForm.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("error_msg", "Email already exists!");
			return "redirect:/admin/new_user";
		}
		
		if(!registrationForm.getConfirmPassword().equals(registrationForm.getPassword())) {
			redirectAttributes.addFlashAttribute("error_msg", "Password do not match!");
			return "redirect:/admin/new_user";
		}
		User user = new User();		
		user.setUsername(registrationForm.getUsername());
	    user.setEmail(registrationForm.getEmail().toLowerCase());
	    user.setPassword(registrationForm.getPassword());
	    
	    Role userRole = roleService.findByName(registrationForm.getRole().toUpperCase());
	    if(userRole == null) {
	    	redirectAttributes.addFlashAttribute("error_msg", "User role should be selected!");
			return "redirect:/admin/new_user";
	    }
	    user.setRole(userRole);
		userService.registerUser(user);
		redirectAttributes.addFlashAttribute("success_msg", "User registered successfully");
		return "redirect:/login";
	}
		
}
