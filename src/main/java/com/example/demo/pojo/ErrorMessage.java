package com.example.demo.pojo;

public class ErrorMessage {
	String message;
	ErrorCode errorCode;
	
	public ErrorMessage(String message, ErrorCode errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public String getMessage() {
		return message;
	}
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
