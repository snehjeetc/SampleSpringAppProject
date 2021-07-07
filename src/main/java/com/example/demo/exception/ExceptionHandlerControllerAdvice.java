package com.example.demo.exception;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(StudentException.class)
	public Object handleStudentException(StudentException exception) {
		JSONObject response = new JSONObject();
		response.put("message", exception.getMessage());
		response.put("status", HttpStatus.BAD_REQUEST);
		return response;
	}
}
