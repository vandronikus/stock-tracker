package com.luidenterprises.stocktracker.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler
	public ResponseEntity<Object> handleException(RuntimeException exception, WebRequest request) {
		String bodyOfResponse = new ObjectMapper().writeValueAsString(request);
		ErrorResponse response = ErrorResponse.builder()
								 .message(exception.getMessage())
								 .
		
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
