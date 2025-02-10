package com.example.calculate.service;

public interface CalculateService {
	
	Double calcBMI(Integer height, Integer weight);
	
	Double calcBMR(Integer height, Integer weight, Integer age, String gender);
	
	Double calcTDEE(Double bmr, Double activityRate);
}
