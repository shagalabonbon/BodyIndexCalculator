package com.example.calculate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
	
	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory){
		
		RedisTemplate<String, Object> template = new RedisTemplate<>();	
		
		template.setConnectionFactory(connectionFactory);                                         // connectionFactory 是 Spring Boot 自動配置的 REDIS 連接工廠
		
		// 設定 Key 與 Value 的序列化方法 ( 資料存進 REDIS 的型態 )
		
		template.setKeySerializer(new StringRedisSerializer());                                   // 將 Key 以 UTF-8 格式儲存  
		
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());                    // 將 Java 物件轉 JSON ( 方法中包含 ObjectMapper )
		
		template.afterPropertiesSet();                                                            // 初始化
		
		return template;
	}
	
}
