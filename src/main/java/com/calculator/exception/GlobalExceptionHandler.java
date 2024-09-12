package com.calculator.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.calculator.model.ErrorResponse;
import com.calculator.operation.Operation;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ExecuteException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleException(ExecuteException ex) {
		return ErrorResponse.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("Something went wrong").build();
	}

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(HttpMessageNotReadableException ex) {
    	String str = "Please correct input request";
    	if(ex.getMessage().contains(Operation.class.getName())) {
    		str = "Please correct operator value. Accpeted value for operation field is one of the fallowing values : "
    					+ Arrays.asList(Operation.values()).toString(); 
    	}
    	if(ex.getMessage().contains(Number.class.getName())) {
    		str = "Please correct initial/operand value. Given value must be in number format"; 
    	}
    	return ErrorResponse.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value()).message(str).build();
	
    }
      
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  @ResponseBody ErrorResponse handleException(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return ErrorResponse.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value()).message( details.toString()).build();
    }
    
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleException(NullPointerException ex) {
        return ErrorResponse.builder()
    				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
    				.message("Something went wrong").build();
    }
}
