package com.luidenterprises.stocktracker.dao;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.SymbolLookupResponse;

public interface StockTrackerDao {
	
	public ResponseEntity<Quote> getSymbolCurrentPrice(String symbol);
	public ResponseEntity<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp);
	public ResponseEntity<SymbolLookupResponse> getSymbolLookup(String symbol);
	
}
