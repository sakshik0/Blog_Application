package com.blog.app.SrpingBoot.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.app.SrpingBoot.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExcpetionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiResponse> resourcenotfoundExpectionalHandling(NotFoundException ex){
		String msg=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(msg,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND	);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		 Map<String, String> errors = new HashMap<>();

		    ex.getBindingResult().getAllErrors().forEach(error -> {
		        String fieldName = ((FieldError) error).getField();
		        String message = error.getDefaultMessage();
		        errors.put(fieldName, message);
		    });

		    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
