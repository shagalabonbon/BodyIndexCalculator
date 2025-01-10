package com.example.calculate.service;

public interface PasswordService {
	
	String encodePassword(String rawPassword);
	
	Boolean verifyPassword(String inputPassword, String encodePassword);
	
}
