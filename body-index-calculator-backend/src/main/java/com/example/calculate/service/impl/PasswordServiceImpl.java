package com.example.calculate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.calculate.service.PasswordService;

public class PasswordServiceImpl implements PasswordService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String encodePassword(String rawPassword) {
		
		String encodePassword = passwordEncoder.encode(rawPassword);
		
		return encodePassword;
	}

	@Override
	public Boolean verifyPassword(String inputPassword, String encodePassword) {  
		
		if( !passwordEncoder.matches(inputPassword, encodePassword)) {
			return false;
		}
		
		return true;
	}

}
