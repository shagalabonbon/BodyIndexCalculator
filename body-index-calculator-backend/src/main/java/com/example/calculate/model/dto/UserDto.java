package com.example.calculate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private String  username;
	private String  age;         // 年齡、身高、體重
	private Integer height;
	private Integer weight;
	
	
}
