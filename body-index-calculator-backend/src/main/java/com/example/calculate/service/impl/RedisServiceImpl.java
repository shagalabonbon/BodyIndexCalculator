package com.example.calculate.service.impl;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
	private RedisTemplate<String,Object> redisTemplate;	
	
	// 撰寫 CRUD 方法
	
	@Override
	public <T> void saveData(String key, T value){	
		
		redisTemplate.opsForValue().set(key, value, 30, TimeUnit.MINUTES);;        // 設定 30 分鐘過期	
			
	}
	
	@Override
	public <T> void saveData(String key, T value, Long time, TimeUnit unit ){	   // 可自訂時間 
		
		redisTemplate.opsForValue().set(key, value, time, unit);
		
	}

	@Override
	public <T> T getData(String key,Class<T> type) {
			
		Object data = redisTemplate.opsForValue().get(key);
		
		if( data == null ) {
			log.warn( "Key：" + key + "無資料");
			return null;
		}
		
		return type.cast(data) ;
	}

	@Override
	public void deleteData(String key) {
		
		redisTemplate.delete(key);
		
	}
	
	
	//	try {                                                                      // 未使用 RedisConfig 配置 Serializer (ObjectMapper) 時，需要手動進行反序列化    
	//		String json = objectMapper.writeValueAsString(value);                     	
	//		redisTemplate.opsForValue().set(key, json, time, unit);	
	//  }catch(Exception ex) {	
	//		log.error("存入 Redis 發生錯誤");	
	//		ex.printStackTrace();
	//	}
	
	

}
