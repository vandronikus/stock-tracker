package com.luidenterprises.stocktracker.dao.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.luidenterprises.stocktracker.dao.StockTrackerDao;
import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.domain.Resolution;
import com.luidenterprises.stocktracker.dto.SymbolLookupResponse;
import com.luidenterprises.stocktracker.util.StockConstants;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Repository
@Primary
public class StockTrackerDaoImplV2 implements StockTrackerDao {

	@Value("${finnhub.base.url}")
	private String baseUrl;
	@Value("${finnhub.api.key}")
	private String finnHubApiKey;
	@Value ("${finnhub.endpoint.realtime.quote}")
	private String realTimeQuoteEndpoint;
	@Value("${finnhub.endpoint.historical.quote}")
	private String historicalQuoteEndpoint;
	@Value("#{${finnhub.endpoint.paths}}")
	private Map<String, String> endpointPaths;
	
	@Autowired
	private HttpClient httpClient;
	
	
	
	@Override
	public ResponseEntity<Quote> getSymbolCurrentPrice(String symbol) {
		
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
		return quoteResponse.block();		
		
	}

	@Override
	public ResponseEntity<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp) {
		
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
		
		return quoteResponse.block();		
	}

	
	
	@Override
	public ResponseEntity<SymbolLookupResponse> getSymbolLookup(String symbol) {
		Mono<ResponseEntity<SymbolLookupResponse>> symbolLookupResponse = WebClient.builder()
																				   .clientConnector(new ReactorClientHttpConnector(httpClient))
																	   			   .baseUrl(baseUrl.concat(endpointPaths.get("symbolLookup")))
																	   			   .defaultHeader(StockConstants.FINNHUB_XAPI_KEY_HEADER, finnHubApiKey)
																	   			   .defaultUriVariables(Collections.singletonMap("q", symbol))
																	   			   .build()
																	   			   .get()
																	   			   .accept(MediaType.APPLICATION_JSON)
																	   			   .retrieve()						
																	   			   .toEntity(SymbolLookupResponse.class);	
		
		 return symbolLookupResponse.block();
		 
		 
	}
	
	
	
	
	
	
	

}
