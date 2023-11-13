package com.springboot.spring_security_custom_login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.spring_security_custom_login.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
	List<Role> findAll();
}
