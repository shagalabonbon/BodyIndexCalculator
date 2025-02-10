package com.example.calculate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.calculate.service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String encodePassword(String rawPassword) {
		
		String encodePassword = passwordEncoder.encode(rawPassword);
		
		return encodePassword;
	}

	@Override
	public Boolean verifyPassword(String inputPassword, String encodedPassword) {  
		
		if (inputPassword == null || encodedPassword == null) {
	        throw new RuntimeException("Passwords must not be null");
	    }
		
		return passwordEncoder.matches(inputPassword, encodedPassword);
	}

}
