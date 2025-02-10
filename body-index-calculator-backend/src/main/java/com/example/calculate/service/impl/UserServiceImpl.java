package com.example.calculate.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.calculate.model.dto.UserDto;
import com.example.calculate.model.entity.User;
import com.example.calculate.repository.UserRepository;
import com.example.calculate.service.PasswordService;
import com.example.calculate.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UserDto> findAllUsers() {	   	
		
		return userRepository.findAll()
				 		     .stream()
				 		     .map( user -> modelMapper.map( user, UserDto.class))
				 		     .toList();
	}

	@Override
	public UserDto findUser(String email) {
		
		User user = userRepository.findUserByEmail(email).orElseThrow( ()-> new RuntimeException() );
		
		return modelMapper.map(user, UserDto.class);
	}
		

	@Override
	public UserDto createUser(UserDto userDto, String rawPassword) {
		
		User user = modelMapper.map(userDto, User.class);
		
		// 非 UserDto 屬性
		
		Set<String> roles = new HashSet<>();	
		roles.add("ROLE_USER");
		
		String encodePassword = passwordService.encodePassword(rawPassword);
		
		// 加入 User 屬性並保存
		
		user.setEnabled(true);
		user.setRoles(roles);
		user.setEncryptPassword(encodePassword);
		userRepository.save( user );
		
		return modelMapper.map(user, UserDto.class);
	}


	@Override
	public UserDto updateUser(Long userId, UserDto userDto) {
		
		User user = userRepository.findById(userId).orElseThrow( ()->new RuntimeException() );
		
		user.setUsername(userDto.getUsername());  // 修改名稱
		user.setBirth(userDto.getBirth());        // 修改生日
		user.setGender(userDto.getGender());      // 修改性別
		
		return modelMapper.map(user, UserDto.class);
	}


	@Override
	public UserDto removeUser(Long userId) {
		
		User user = userRepository.findById(userId).orElseThrow( ()->new RuntimeException() );
		
		user.setEnabled(false);   // 禁用帳戶
		
		return modelMapper.map(user, UserDto.class);
	}
	
	
	
	
}
