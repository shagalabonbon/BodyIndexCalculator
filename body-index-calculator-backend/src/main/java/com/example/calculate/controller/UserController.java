package com.example.calculate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.calculate.model.dto.UserDto;
import com.example.calculate.response.ApiResponse;
import com.example.calculate.service.UserService;


@RestController
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
	
		
	
	
}
