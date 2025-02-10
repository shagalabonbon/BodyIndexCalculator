package com.example.calculate.model.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    	id;
	private String  	username;
	private String  	encryptPassword;
	
	private boolean 	enabled;               // 帳戶是否啟用 
	private Set<String> roles;                 // 帳戶權限
//	private boolean 	accountNonExpired;     // 帳戶是否過期
//  private boolean 	credentialsNonExpired; // 密碼是否過期 
//  private boolean 	accountNonLocked;      // 帳戶是否被鎖定 ( 密碼錯誤次數過多 )
	
	private String  	email;		           // 基本資料
	private String      gender;
	private String  	birth;                 
	
	@OneToMany(mappedBy = "user")
	private List<BodyIndexRecord> records;
	
}
