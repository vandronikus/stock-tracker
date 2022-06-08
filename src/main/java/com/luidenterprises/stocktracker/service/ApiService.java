package com.luidenterprises.stocktracker.service;

import java.util.Optional;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;

public interface ApiService {
	
	public Optional<Quote> getSymbolCurrentPrice(String symbol);

	public Optional<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp);

}
