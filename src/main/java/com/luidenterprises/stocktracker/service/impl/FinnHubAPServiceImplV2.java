package com.luidenterprises.stocktracker.service.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.domain.Resolution;
import com.luidenterprises.stocktracker.exception.ServiceException;
import com.luidenterprises.stocktracker.service.FinnHubApiService;
import com.luidenterprises.stocktracker.util.StockConstants;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
@Service
@Primary
@Slf4j	
public class FinnHubAPServiceImplV2 implements FinnHubApiService {
	
	private final Function<ClientResponse, Mono<? extends Throwable>> errorFunctionHandler = response -> {
		log.error("Failed to retrieve FinnHub response, HTTP status code: {}, cause: {}", response.rawStatusCode(), response.toString());
		return Mono.error(new ServiceException(response.createException().block(), response.rawStatusCode(), response.toString()));
	};
	
	
	@Value("${finnhub.base.url}")
	private String baseUrl;
	@Value("${finnhub.api.key}")
	private String finnHubApiKey;
	@Value ("${finnhub.endpoint.realtime.quote}")
	private String realTimeQuoteEndpoint;
	@Value("${finnhub.endpoint.historical.quote}")
	private String historicalQuoteEndpoint;
	@Autowired
	private HttpClient httpClient;
	

	@Override
	public Optional<Quote> getSymbolCurrentPrice(String symbol) {
		
		Mono<ResponseEntity<Quote>> quoteResponse = WebClient.builder()
															 .clientConnector(new ReactorClientHttpConnector(httpClient))
															 .baseUrl(baseUrl + realTimeQuoteEndpoint)
															 .defaultHeader(StockConstants.FINNHUB_XAPI_KEY_HEADER, finnHubApiKey)
															 .defaultUriVariables(Collections.singletonMap("s", symbol))
															 .build()
															 .get()
															 .accept(MediaType.APPLICATION_JSON)
															 .retrieve()
															 .toEntity(Quote.class);
		return Optional.ofNullable(quoteResponse.block().getBody());		
		
	}

	@Override
	public Optional<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp) {
		
		Mono<ResponseEntity<QuoteCandleStick>> quoteResponse = WebClient.builder()
												.clientConnector(new ReactorClientHttpConnector(httpClient))
									   			.baseUrl(baseUrl.concat(historicalQuoteEndpoint))
									   			.defaultHeader(StockConstants.FINNHUB_XAPI_KEY_HEADER, finnHubApiKey)
									   			.defaultUriVariables(Map.of("s", symbol, "f", fromUnixTimestamp, "t", toUnixTimeStamp, "r", Resolution.DAY.getResolutionCode()))
									   			.build()
									   			.get()
									   			.accept(MediaType.APPLICATION_JSON)
									   			.retrieve()									   			
									   			.toEntity(QuoteCandleStick.class);
		
		return Optional.ofNullable(quoteResponse.block().getBody());
			
		
	}
	
	
	

}
