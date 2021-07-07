package com.example.demo.exception;
@SuppressWarnings("unused")
public class StudentException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1l;
	public enum ExceptionType{
		INVALID_INPUT,
		RECORD_NOT_FOUND
	}
	
	private final ExceptionType type;
	public StudentException(String message, ExceptionType type) {
		super(message +" : " + type.toString());
		this.type = type;
	}
	
}
