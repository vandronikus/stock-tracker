package com.luidenterprises.stocktracker.exception;

import java.time.Instant;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luidenterprises.stocktracker.config.aspect.LogMethodExecution;
import com.luidenterprises.stocktracker.util.StockConstants;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler
	@LogMethodExecution
	public ResponseEntity<Object> handleException(RuntimeException exception, WebRequest request) throws JsonProcessingException {
		//String bodyOfResponse = StockUtils.getObjectWriter().writeValueAsString(request);
		ErrorResponse response = ErrorResponse.builder()
								 .message(exception.getMessage())
								 .statusCode("0")
								 .timestamp(StockConstants.DATE_FORMATTER.format(Instant.now()))
								 .stacktrace(exception.getStackTrace().toString())
								 .build();	
		
        return handleExceptionInternal(exception, response, 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
