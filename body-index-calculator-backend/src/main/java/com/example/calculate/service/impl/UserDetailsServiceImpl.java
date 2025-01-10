package com.example.calculate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.calculate.repository.UserRepository;

// UserDetailsService 介面用於 Spring Security 驗證用戶身分  

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.example.calculate.model.entity.User user = userRepository.findUserByUsername(username)
				                                                     .orElseThrow(()->new UsernameNotFoundException("")); 
		
		// Security 的 User ( UserDetails ) 提供了身份驗證和授權相關的核心屬性，包含名稱、密碼、權限
		
		return new User( user.getUsername(),
				         user.getEncryptPassword(),
				         user.getRoles().stream()
				                        .map(SimpleGrantedAuthority::new)
				                        .toList()
	    );
	}

}
