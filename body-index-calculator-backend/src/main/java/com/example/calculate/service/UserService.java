package com.example.calculate.service;

import java.util.List;
import java.util.Optional;

import com.example.calculate.model.dto.UserDto;


public interface UserService {
	
	// 所有用戶
	List<UserDto> findAllUsers();
	
	// 尋找用戶
	UserDto findUser(String email);
	
	// 建立
	UserDto createUser(UserDto userDto, String rawPassword);
	
	// 更新
	UserDto updateUser(Long userId,UserDto userDto);
	
	// 移除
	UserDto removeUser(Long userId);

	
}
