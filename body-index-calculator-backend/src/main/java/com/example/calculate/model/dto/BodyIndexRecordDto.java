package com.example.calculate.model.dto;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyIndexRecordDto {
	
	private Timestamp date;     // 紀錄日期
	private Integer   weight;   // 體重
	private Integer   height;   // 身高
	private Double    bmi;      // 指數
	private Double    bmr;
	private Double    tdee;
	
}
