package com.luidenterprises.stocktracker.service;

import java.util.Optional;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.SymbolLookupResponse;

public interface StockTrackerService {
	
	//public Mono<ResponseEntity<Quote>> getMonoSymbolCurrentPrice(String symbol);
	//public Mono<ResponseEntity<QuoteCandleStick>> getMonoSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp);
	
	public Optional<Quote> getSymbolCurrentPrice(String symbol);
	public Optional<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp);
	public SymbolLookupResponse getSymbolLookup(String symbol);

}
