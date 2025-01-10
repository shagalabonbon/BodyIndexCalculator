package com.example.calculate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.calculate.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();  // 使用 BCrypt 演算法加密 ( SHA 256 + 加鹽 )
	}
	
			
	@Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
		
		// AuthenticationProvider 負責實際執行認證流程
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);     // 用戶資料
        authenticationProvider.setPasswordEncoder(passwordEncoder());   // 加密方式
        
        return authenticationProvider;
    }
	
	// AuthenticationManager 處理驗證請求
	@Bean
    AuthenticationManager authenticationManager() {
        
		return new ProviderManager(daoAuthenticationProvider());
        
        // 若有多個驗證方式，使用：List.of(daoAuthenticationProvider(), customAuthenticationProvider())      
    }
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
		
		http
			.authorizeHttpRequests( authorizeHttpRequests ->
	        	authorizeHttpRequests
	            	.requestMatchers("/**")
	            	.permitAll()            	
	            	.anyRequest()                    // 其他請求需要登錄
	             	.authenticated()                             
	        )
	    	
	        .formLogin(formLogin ->                  // 使用表單登錄功能來驗證用戶
	            formLogin
	                .loginPage("/login")             // 自定義登錄頁面
	                .loginProcessingUrl("/login")    // 處理登錄請求的 URL ( security 自動處裡，需要複寫 UserDetailsService )
	                .defaultSuccessUrl("/home",true) // 登錄成功後的預設頁面
	                .failureUrl("/login")        	 // 登錄失敗後的頁面          ****** 待處理
	                .permitAll()                     // 不須授權
	        )
	        
	        .logout(logout -> 
	            logout
	            	.logoutUrl("/logout")            // 處理登出請求的 URL
	            	.logoutSuccessUrl("/login")      // 登出成功後的頁面
	            	.invalidateHttpSession(true)     // 清除 session
	            	.permitAll()                     // 允許所有請求
	        )
	    	
	    	.csrf(csrf -> csrf.disable())                      // 禁用 CSRF
	    	
	    	.authenticationManager(authenticationManager);     // 驗證請求處理
    
    	return http.build();         						   // http.build() 建構配置
	}
	
	
	
        
}
