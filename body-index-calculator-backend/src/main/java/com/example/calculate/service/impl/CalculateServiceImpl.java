package com.example.calculate.service.impl;

import org.springframework.stereotype.Service;

import com.example.calculate.service.CalculateService;

@Service
public class CalculateServiceImpl implements CalculateService {

	@Override
	public Double calcBMI(Integer height, Integer weight) {
		
		Double bmi = weight/Math.pow(height/100,2);

		return bmi;
	}

	@Override
	public Double calcBMR(Integer height, Integer weight, Integer age, String gender) {	
		
		Double bmr = 0.0 ;	
		
		if (gender.equals("male")) {		
			bmr = (13.7*weight)+(5.0*height)-(6.8*age)+66;
		}
		
		if (gender.equals("female")) {			
			bmr = (9.6*weight)+(1.8*height)-(4.7*age)+655;
		}
		
		return bmr;
	}

	// BMR(男)=(13.7×體重(公斤))+(5.0×身高(公分))-(6.8×年齡)+66
	// BMR(女)=(9.6×體重(公斤))+(1.8×身高(公分))-(4.7×年齡)+655

	@Override
	public Double calcTDEE(Double bmr, Double activityRate) {

		Double tdee = bmr * activityRate;

		return tdee;
	}

	// TDEE = BMR x 活動量

}
