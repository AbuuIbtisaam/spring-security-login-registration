package com.springboot.spring_security_custom_login.dto;

public class UserData {

	private String username;
	private String email;
	private String roleName;
	
	public UserData(String username, String email, String roleName) {
		this.username=username;
		this.email=email;
		this.roleName=roleName;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
		
}
