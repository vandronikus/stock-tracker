package com.luidenterprises.stocktracker.service.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.domain.Resolution;
import com.luidenterprises.stocktracker.exception.ServiceException;
import com.luidenterprises.stocktracker.service.ApiService;
import com.luidenterprises.stocktracker.util.StockConstants;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@Service
@Primary
@Slf4j
public class FinnHubAPServiceImplV2 implements ApiService {
	
	
	@Value("${finnhub.base.url}")
	private String baseUrl;
	@Value("${finnhub.api.key}")
	private String finnHubApiKey;
	@Value ("${finnhub.endpoint.realtime.quote}")
	private String realTimeQuoteEndpoint;
	@Value("${finnhub.endpoint.historical.quote}")
	private String historicalQuoteEndpoint;
	

	@Override
	public Optional<Quote> getSymbolCurrentPrice(String symbol) {
		Mono<Quote> quoteResponse = WebClient.builder()
						   			.baseUrl(baseUrl + realTimeQuoteEndpoint)
						   			.defaultHeader(StockConstants.FINNHUB_XAPI_KEY_HEADER, finnHubApiKey)
						   			.defaultUriVariables(Collections.singletonMap("s", symbol))
						   			.build()
						   			.get()
						   			.accept(MediaType.APPLICATION_JSON)
						   			.retrieve()
						   			.onStatus(HttpStatus::isError, StockConstants.FunctionConstants.errorFunctionHandler)
						   			.bodyToMono(Quote.class);
		return quoteResponse.blockOptional();		
		
	}

	@Override
	public Optional<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp) {
		Mono<QuoteCandleStick> quoteResponse = WebClient.builder()
									   			.baseUrl(baseUrl + historicalQuoteEndpoint)
									   			.defaultHeader(StockConstants.FINNHUB_XAPI_KEY_HEADER, finnHubApiKey)
									   			.defaultUriVariables(Map.of("s", symbol, "f", fromUnixTimestamp, "t", toUnixTimeStamp, "r", Resolution.DAY.getResolutionCode()))
									   			.build()
									   			.get()
									   			.accept(MediaType.APPLICATION_JSON)
									   			.retrieve()
									   			.onStatus(HttpStatus::isError, StockConstants.FunctionConstants.errorFunctionHandler)
									   			.bodyToMono(QuoteCandleStick.class)
									   			.onErrorMap(throwable -> {
									   				return new ServiceException(throwable);
									   			});
									   			
									   			
		return quoteResponse.blockOptional();	
		
	}
	
	
	

}
