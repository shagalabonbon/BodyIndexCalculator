package com.example.calculate.model.entity;

import java.security.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyIndexRecord {
	
	@Id
	private Long 	  id;
	
	private Timestamp date;     // 紀錄日期
	private Integer   weight;   // 體重
	private Integer   height;   // 身高
	private Double    bmi;      // 指數
	private Double    bmr;
	private Double    tdee;
	
	@ManyToOne
	private User      user;

}
