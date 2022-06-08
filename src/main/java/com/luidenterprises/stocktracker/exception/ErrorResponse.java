package com.luidenterprises.stocktracker.exception;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ErrorResponse {
	
	private String timestamp;
	private String message;
	private String statusCode;
	private String stacktrace;
	

}
