package com.example.calculate.service.impl;

import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.calculate.model.dto.UserDto;
import com.example.calculate.model.entity.User;
import com.example.calculate.repository.UserRepository;
import com.example.calculate.service.AuthService;
import com.example.calculate.service.JwtService;
import com.example.calculate.service.PasswordService;
import com.example.calculate.service.RedisService;

import io.jsonwebtoken.Claims;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public String login(String email, String password) {    
		
		User loginUser = userRepository.findUserByEmail(email).orElseThrow( ()->new RuntimeException());
		
		UserDto loginUserDto = modelMapper.map(loginUser,UserDto.class);
		
		// 驗證密碼
		if(!passwordService.verifyPassword(password, loginUser.getEncryptPassword())) {
			throw new RuntimeException("Invalid email or password");
		}
		
		// 產生憑證
		String token = jwtService.generateJwt(loginUser.getId());
		
		// 登入後將使用者存入 REDIS
		redisService.saveData( loginUserDto.getId().toString() , loginUserDto );   // 將 ID 設入 Key -> 不同使用者 ( userID ) / 不同裝置 ( sessionID ) 
		
		redisService.saveData( jwtService.parseJwt(token).getId() , "login" );
			
		return token;  // 登入後回傳用戶憑證
	
	}

	@Override
	public void logout(String token) { 
		
		// 將憑證加入黑名單直到過期
		
		Claims claims = jwtService.parseJwt(token);
		
		Long expireTime = claims.getExpiration().getTime();
		
		Long currentTime = System.currentTimeMillis();
		
		String jti = claims.getId();
		
		if( expireTime > currentTime ) {
			
			long ttl = expireTime - currentTime;
			
			redisService.saveData( jti, "logout", ttl, TimeUnit.MILLISECONDS );  // 使用 JWT ID 作為 Key 加入黑名單
			
		}		  
		
	}

}
