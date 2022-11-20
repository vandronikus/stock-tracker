package com.luidenterprises.stocktracker.service.impl;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.luidenterprises.stocktracker.config.aspect.LogMethodExecution;
import com.luidenterprises.stocktracker.dao.StockTrackerDao;
import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.BasicFinancialsDTO;
import com.luidenterprises.stocktracker.dto.CompanyProfileDTO;
import com.luidenterprises.stocktracker.dto.SymbolDataDTO;
import com.luidenterprises.stocktracker.dto.SymbolLookupResponseDTO;
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
	public Optional<SymbolDataDTO> getSymbolLookup(String symbol) {
		ResponseEntity<SymbolLookupResponseDTO> response = stockTrackerDao.getSymbolLookup(symbol);				
		Optional<SymbolDataDTO> symbolDataOptional 		 = Optional.ofNullable(response.getBody())
																   .get()
																   .getResult()
																   .stream()
																   .filter(symbolData -> symbolData.getSymbol().equalsIgnoreCase(symbol))
																   .findFirst();	
		return symbolDataOptional;
	}

	@Override
	@LogMethodExecution
	public Optional<CompanyProfileDTO> getCompanyProfile(String symbol) {
		ResponseEntity<CompanyProfileDTO> response = stockTrackerDao.getCompanyProfile(symbol);		
		return Optional.ofNullable(response.getBody());		
	}

	@Override
	public Optional<BasicFinancialsDTO> getBasicFinancials(String symbol) {
		ResponseEntity<BasicFinancialsDTO> response = stockTrackerDao.getBasicFinancials(symbol);
		return Optional.ofNullable(response.getBody());
	}

	@Override
	@Async
	public Future<String> doSomething() throws InterruptedException {
		int sleepTime = 1000 * (int)(Math.random() * 10);
		log.info("Method started in thread {} ", Thread.currentThread().getName());
		Thread.sleep(sleepTime);
		log.info("Method finished in thread {} ", Thread.currentThread().getName());
		return new AsyncResult<String>("done - sleep time for thread " + Thread.currentThread().getName() + " - " + sleepTime);
		
		
	}
	
	
	
	
	
	
	

	
	
	
	

}
