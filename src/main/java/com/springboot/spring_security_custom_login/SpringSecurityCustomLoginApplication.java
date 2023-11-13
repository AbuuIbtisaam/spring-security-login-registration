package com.springboot.spring_security_custom_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityCustomLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityCustomLoginApplication.class, args);
		System.out.println("Custom Login App Started"); 
	}

}
