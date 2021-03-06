package com.luidenterprises.stocktracker.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.luidenterprises.stocktracker.dao.StockTrackerDao;
import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.domain.Resolution;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j	
public class StockTrackerDaoImplV1 {
	
	
	@Value("${finnhub.api.key}")
	private String finnHubApiKey;
	@Value("${finnhub.base.url}")
	private String baseUrl;
	@Value ("${finnhub.endpoint.realtime.quote}")
	private String realTimeQuoteEndpoint;
	@Value("${finnhub.endpoint.historical.quote}")
	private String historicalQuoteEndpoint;
	@Autowired
	RestTemplate finnHubRestTemplate;
	


	
	//@Override
	public Optional<Quote> getSymbolCurrentPrice(String symbol) {		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Finnhub-Token", finnHubApiKey);
		ResponseEntity<Quote> result = finnHubRestTemplate.exchange(baseUrl + String.format(realTimeQuoteEndpoint, symbol), HttpMethod.GET, new HttpEntity<Object>(headers),  Quote.class);		
		return Optional.ofNullable(result.getBody());
	}

	//@Override
	public Optional<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long from, long to) {	
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Finnhub-Token", finnHubApiKey);
		ResponseEntity<QuoteCandleStick> result 			= finnHubRestTemplate.exchange(baseUrl + String.format(historicalQuoteEndpoint, symbol, Resolution.DAY.getResolutionCode(), from, to), HttpMethod.GET, new HttpEntity<Object>(headers), QuoteCandleStick.class);
		Optional<QuoteCandleStick> optionalQuoteCandleStick = Optional.ofNullable(result.getBody());
		
		if (optionalQuoteCandleStick.isPresent()) {
			optionalQuoteCandleStick.get().setToUnixTimeStamp(to);
			optionalQuoteCandleStick.get().setFromUnixTimeStamp(from);
			
		}
		
		
		return optionalQuoteCandleStick;
		
	}
	
	

}
