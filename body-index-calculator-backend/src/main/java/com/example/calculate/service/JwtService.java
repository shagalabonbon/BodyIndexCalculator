package com.example.calculate.service;

import java.security.Key;

import io.jsonwebtoken.Claims;

public interface JwtService {
		
	String  generateJwt(Long userId);       // 產生 JWT
	
	Claims  parseJwt(String jwt);           // 解析 JWT ( 將資料存在內才使用 )
	
	Boolean isJwtExpired(String jwt);       // 驗證有效期
	
	Key     generalKey();
	
	String  getUUID();                      // 產生隨機 UUID （Universally Unique Identifier，通用唯一識別碼）
	
	
	
}
