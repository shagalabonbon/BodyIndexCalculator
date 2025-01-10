package com.example.calculate.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.calculate.model.dto.UserDto;
import com.example.calculate.model.entity.User;
import com.example.calculate.repository.UserRepository;
import com.example.calculate.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
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
	public Optional<UserDto> findUser(String email) {
		
		Optional<User> optUser = userRepository.findUserByEmail(email);
		
		return Optional.of( modelMapper.map(optUser, UserDto.class) ) ;
	}
		

	@Override
	public Optional<UserDto> createUser(UserDto userDto) {
		
		User user = modelMapper.map(userDto, User.class);
		
		userRepository.save( user );
		
		return Optional.of( modelMapper.map(user, UserDto.class) );
	}


	@Override
	public Optional<UserDto> updateUser(Long userId, UserDto userDto) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


	@Override
	public Optional<UserDto> removeUser(Long userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	
	
	
}
