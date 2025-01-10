package com.example.calculate.service;

import java.util.List;
import java.util.Optional;

import com.example.calculate.model.dto.UserDto;


public interface UserService {
	
	List<UserDto> findAllUsers();
	
	Optional<UserDto> findUser(String email);
	
	Optional<UserDto> createUser(UserDto userDto);
	
	Optional<UserDto> updateUser(Long userId,UserDto userDto);
	
	Optional<UserDto> removeUser(Long userId);

	
}
