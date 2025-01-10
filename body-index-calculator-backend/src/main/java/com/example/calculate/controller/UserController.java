package com.example.calculate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.calculate.model.dto.UserDto;
import com.example.calculate.response.ApiResponse;
import com.example.calculate.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/bic")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users")
	public ResponseEntity<ApiResponse<List<UserDto>>> getAllUser() {   // 前後端分離，使用 ResponseEntity 提供自訂回應格式
		
		List<UserDto> userDtos = userService.findAllUsers();
		
		if(userDtos.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404,"NOT FOUND"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("Search Successed", userDtos));
	}
	
	
	@PostMapping("/user")
	public ResponseEntity<ApiResponse<UserDto>> createNewUser(@ModelAttribute UserDto userDto, @RequestParam String password) {
		
		Boolean isUserPresent = userService.findAllUsers().stream().anyMatch( user -> user.getEmail().equals(userDto.getEmail()) );
		
		if(isUserPresent) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "USER ALREADY EXISTS"));
		}
		
		UserDto newUserDto = userService.createUser(userDto, password);
		
		return ResponseEntity.ok(ApiResponse.success("USER CREATED", newUserDto));
	}
	
	
	
}
