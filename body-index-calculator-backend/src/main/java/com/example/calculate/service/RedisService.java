package com.example.calculate.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
	
	<T> void saveData(String key, T value);
	
	<T> void saveData(String key, T value, Long time, TimeUnit unit );   // 可自訂存取時間
	
	<T> T    getData(String key, Class<T> type);
	
	void     deleteData(String key);
	
}
