package com.springboot.spring_security_custom_login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.spring_security_custom_login.entity.User;
import com.springboot.spring_security_custom_login.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void registerUser(User user) {
		// Hash the user's password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // Set the user's password to the hashed password
        user.setPassword(encodedPassword);

        // Save the user to the database
        userRepository.save(user);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}