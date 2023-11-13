package com.springboot.spring_security_custom_login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring_security_custom_login.dto.UserData;
import com.springboot.spring_security_custom_login.entity.User;

@Service
public class UserDataService {

	private final UserService userService;
	
	@Autowired
	public UserDataService(UserService userService) {
		this.userService = userService;
	}
	
	public UserData getUserData(String username) {
		User user = userService.findByUsername(username);
		UserData userData = new UserData(user.getUsername(), user.getEmail(), user.getRole().getName());
		return userData; 
	}
}
