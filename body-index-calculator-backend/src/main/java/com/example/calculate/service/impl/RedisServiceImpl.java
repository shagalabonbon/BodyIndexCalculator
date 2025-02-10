package com.example.calculate.service.impl;

import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.calculate.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
	
	// 注入 RedisTemplate 依賴
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	// 撰寫 CRUD 方法
	
	@Override
	public <T> void saveData(String key, T value){	
		
		try {
			
			String json = objectMapper.writeValueAsString(value);                     // 物件轉為 JSON 格式 ( String ) 
			
			stringRedisTemplate.opsForValue().set(key, json, 30, TimeUnit.MINUTES);;  // 設定 30 分鐘過期	
			
		}catch(Exception ex) {	
			
			log.error("存入 Redis 發生錯誤");	
			ex.printStackTrace();
		}
	}
	
	@Override
	public <T> void saveData(String key, T value, Long time, TimeUnit unit ){	            // 可自訂時間 
		
		try {
			
			String json = objectMapper.writeValueAsString(value);                     
			
			stringRedisTemplate.opsForValue().set(key, json, time, unit);
			
		}catch(Exception ex) {	
			
			log.error("存入 Redis 發生錯誤");	
			ex.printStackTrace();
		}
	}
	

	@Override
	public <T> T getData(String key, Class<T> targetClass) {
		
		String json = stringRedisTemplate.opsForValue().get(key);	
		T data = null;
		
		if (json == null) {
	        return null;      // 檢查 REDIS 是否有資料
	    }
		
		try {
			data = objectMapper.readValue( json,targetClass );	 // 將 JSON 轉回 Java 物件				
		}catch(Exception ex) {			
			log.error("讀取 Redis 發生錯誤");	
			ex.printStackTrace();
		}
		
		return data;
	}

	@Override
	public void deleteData(String key) {
		
		stringRedisTemplate.delete(key);
	}

}
