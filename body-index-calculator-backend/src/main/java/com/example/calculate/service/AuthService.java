package com.example.calculate.service;

public interface AuthService {
	
	public String login(String email, String password);
	
	public void logout(String token);	
	
}
