package com.company.project.exception;

public class CalculationSessionNotFoundException extends Exception {
	String message;
	
	public CalculationSessionNotFoundException() {
		super();
	}

	public CalculationSessionNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
