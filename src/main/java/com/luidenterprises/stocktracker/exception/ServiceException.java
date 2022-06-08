package com.luidenterprises.stocktracker.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ServiceException extends RuntimeException {
	
	private int httpStatus;
	private static final long serialVersionUID = 4963912269374273979L;
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	
	public ServiceException(String message, int httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
}
