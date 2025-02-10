package com.example.calculate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Long    id;
	private String  username;   // 年齡、性別
	private String  email;	
	private String  birth;         
	private String  gender;
	
}
