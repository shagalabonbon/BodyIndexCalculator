package com.example.calculate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
	
	private Integer status;
	private String  message;
	private T       data;
	
	
	// 成功回應
	
	public static <T> ApiResponse<T> success(String message, T data){         // 第一個 <T> 表示該方法為「泛型方法」		
		return new ApiResponse<T>( 200,message,data );
	}
	
	// 失敗回應
	
	public static <T> ApiResponse<T> error(Integer status, String message){  		
		return new ApiResponse<T>( status,message,null );
	}
	
}
