package com.example.calculate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig_X {
	
	@Bean
	private RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory){
		
		RedisTemplate<String, Object> template = new RedisTemplate<>();	
		
		template.setConnectionFactory(connectionFactory);                                         // connectionFactory 是 Spring Boot 自動配置的 Redis 連接工廠
		
		template.setKeySerializer(new StringRedisSerializer());                                   // 將 Key 以 UTF-8 格式儲存
		
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer(new ObjectMapper()));  // 將 Java 物件轉為 JSON 
		
		template.afterPropertiesSet();   // 初始化
		
		return template;
	}
}
