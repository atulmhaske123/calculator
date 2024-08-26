package com.test.calculator.exception;

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

import com.test.calculator.model.ErrorResponse;
import com.test.calculator.operation.Operation;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(HttpMessageNotReadableException ex) {
    	if(ex.getMessage().contains(Operation.class.getName())) {
    		String str = "Please correct operator value. Accpeted value for operation field is one of the fallowing values : "+Arrays.asList(Operation.values()).toString(); 
    		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), str);
    	}
    	if(ex.getMessage().contains(Number.class.getName())) {
    		String str = "Please correct initial/operand value. Given value must be in number format"; 
    		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), str);
    	}
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Please correct input request");
    }
      
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  @ResponseBody ErrorResponse handleException(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
    	return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), details.toString());
    }
    
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleException(NullPointerException ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
    }
}
