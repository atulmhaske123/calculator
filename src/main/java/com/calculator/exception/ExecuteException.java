package com.calculator.exception;

import lombok.Data;

@Data
public class ExecuteException extends RuntimeException{
	
	public ExecuteException(String message) {
		super(message);
	}
	public ExecuteException(String message, Throwable cause) {
		super(message, cause);
	}
	public ExecuteException(Throwable cause) {
		super(cause);
	}
}
