package com.luidenterprises.stocktracker.service.impl;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.luidenterprises.stocktracker.config.aspect.LogMethodExecution;
import com.luidenterprises.stocktracker.dao.StockTrackerDao;
import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.SymbolLookupResponse;
import com.luidenterprises.stocktracker.exception.ServiceException;
import com.luidenterprises.stocktracker.service.StockTrackerService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j	
public class StockTrackerServiceImpl implements StockTrackerService {
	
	@Autowired
	StockTrackerDao stockTrackerDao;
	
	private final Function<ClientResponse, Mono<? extends Throwable>> errorFunctionHandler = response -> {
		log.error("Failed to retrieve FinnHub response, HTTP status code: {}, cause: {}", response.rawStatusCode(), response.toString());
		return Mono.error(new ServiceException(response.createException().block(), response.rawStatusCode(), response.toString()));
	};

	@Override
	@LogMethodExecution
	public Optional<Quote> getSymbolCurrentPrice(String symbol) {
		return Optional.ofNullable(stockTrackerDao.getSymbolCurrentPrice(symbol).getBody());
	}

	@Override
	@LogMethodExecution
	public Optional<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp) {
		return Optional.ofNullable(stockTrackerDao.getSymbolHistoricalPrice(symbol, fromUnixTimestamp, toUnixTimeStamp).getBody());
	}

	@Override
	@LogMethodExecution
	public SymbolLookupResponse getSymbolLookup(String symbol) {
		ResponseEntity<SymbolLookupResponse> response = stockTrackerDao.getSymbolLookup(symbol);
		return response.getBody();
	}
	
	
	
	
	
	
	

	
	
	
	

}
