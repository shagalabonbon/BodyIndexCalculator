package com.example.calculate.service;

import com.example.calculate.model.dto.UserDto;

public interface AuthService {
	
	public UserDto login(String email, String password);
	
	public void logout();
	
	// 生成 JWT 令牌
	public String generateToken(UserDto userDto);
}
