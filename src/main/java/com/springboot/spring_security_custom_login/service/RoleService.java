package com.springboot.spring_security_custom_login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring_security_custom_login.entity.Role;
import com.springboot.spring_security_custom_login.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
	public void addRole(Role role) {
		role.setName(role.getName().toUpperCase());
		roleRepository.save(role);
	}
	
	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}
}
