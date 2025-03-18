package com.example.calculate.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.calculate.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	
	private static final long expireTime = 30 * 60 * 1000;  // 預設 30 分鐘 
	
	private static final String secretKey = "key";          // Base64 密鑰
	
	// @param ttlMillis： 到期時間 ( Time-To-Live )
	
	@Override
	public String generateJwt(Long userId) {  
		
		JwtBuilder jwtBuilder =  Jwts.builder()
				                     .setIssuer("BicSystem")                                             // 簽發單位
									 .setSubject(userId.toString())                                      // 使用者資訊 ( 只存入 userID )
									 .setIssuedAt(new Date())                                            // 簽發時間
									 .setExpiration(new Date(System.currentTimeMillis() + expireTime))   // 過期時間
									 
								     .signWith(generalKey())                                             // 使用密鑰簽名
								     .setId(getUUID());                                                  // JWT 唯一識別碼 ( jti )
								     
		return jwtBuilder.compact();                      // 生成 JWT 字串
	}

	@Override
	public Claims parseJwt(String jwt) {                  // 提取 Claims 以使用其中資訊 ( 簽發單位、使用者... )
		
		Key secretKey = generalKey();
		
		Claims jwtClaims = Jwts.parserBuilder()
				               .setSigningKey(secretKey)
				               .build()
				               .parseClaimsJwt(jwt)
				               .getBody();	
		return jwtClaims;
	}

	@Override
	public Boolean isJwtExpired(String jwt) {
		
		Date expDate = parseJwt(jwt).getExpiration();     // 從 Claims 提取 expDate
		
		return expDate.before(new Date());                // 當前時間與到期時間進行比較，過期回傳 true
	}
	

	
	@Override
	public Key generalKey() {
		
		byte[] keyByte = Decoders.BASE64.decode(secretKey);  // Decoders 用於對 Base64 或 Base64URL 編碼的字串進行解碼 ( jsonwebtoken.io )
	
		Key key = Keys.hmacShaKeyFor(keyByte);               // Keys 將 byte[] ( 通常是 Base64 解碼後的密鑰 ) 轉換為 SecretKey 物件 ( jsonwebtoken.security )
		
		return key;
	}

	@Override
	public String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	
	
	
	
	
	
	
	

}
