package com.luidenterprises.stocktracker.util;

import java.util.function.Function;

import javax.management.RuntimeErrorException;

import org.springframework.web.reactive.function.client.ClientResponse;

import com.luidenterprises.stocktracker.exception.ServiceException;

import reactor.core.publisher.Mono;

public interface StockConstants {
	
	public interface FunctionConstants {
		Function<ClientResponse, Mono<? extends Throwable>> errorFunctionHandler = response -> {
				return Mono.error(new ServiceException(response.toString(), response.rawStatusCode()));
			};
		
	}
	public static final String FINNHUB_XAPI_KEY_HEADER =  "X-Finnhub-Token";

}
