package com.luidenterprises.stocktracker.service;

import java.util.Optional;
import java.util.concurrent.Future;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.BasicFinancialsDTO;
import com.luidenterprises.stocktracker.dto.CompanyProfileDTO;
import com.luidenterprises.stocktracker.dto.SymbolDataDTO;

public interface StockTrackerService {
	
	//public Mono<ResponseEntity<Quote>> getMonoSymbolCurrentPrice(String symbol);
	//public Mono<ResponseEntity<QuoteCandleStick>> getMonoSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp);
	
	public Optional<Quote> getSymbolCurrentPrice(String symbol);
	public Optional<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp);
	public Optional<SymbolDataDTO> getSymbolLookup(String symbol);
	public Optional<CompanyProfileDTO> getCompanyProfile(String symbol);
	public Optional<BasicFinancialsDTO> getBasicFinancials(String symbol);
	
	public Future<String> doSomething() throws InterruptedException;

}
