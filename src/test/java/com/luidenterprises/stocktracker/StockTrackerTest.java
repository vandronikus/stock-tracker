package com.luidenterprises.stocktracker;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.SymbolLookupResponse;
import com.luidenterprises.stocktracker.service.StockTrackerService;
import com.luidenterprises.stocktracker.util.StockUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class StockTrackerTest {
	
	@Autowired
	StockTrackerService apiService;
	
	@Test
	public void testFinnHubHistorialSymbolRequestApi() {		
		String to = StockUtils.toStringUnixTime(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.ofHours(-4)));
		String from = StockUtils.toStringUnixTime(LocalDate.now().atStartOfDay().minus(10, ChronoUnit.DAYS).toInstant(ZoneOffset.ofHours(-4)));
		
		Optional<QuoteCandleStick> quoteList = apiService.getSymbolHistoricalPrice("TSLA", Long.parseLong(from), Long.parseLong(to));		
		Assert.isTrue(quoteList.isPresent(), "quoteList is not null and present.");		
		log.info("quoteList: ", quoteList.get().toString());
	}
	
	@Test
	public void testFinnHubCurrentPriceApi() {
		Optional<Quote> quoteResponse = apiService.getSymbolCurrentPrice("TSLA");		
		Assert.notNull(quoteResponse.get(), "quoteResponse is not null.");	
		log.info("quoteResponse: ", quoteResponse.get().toString());
	}
	
	
	@Test
	public void testFinnHubSymbolLookup() {
		SymbolLookupResponse symbolResponse = apiService.getSymbolLookup("TSLA");
		Assert.notNull(symbolResponse, "SymbolLookupResponse should not be null");
		Assert.notEmpty(symbolResponse.getResult(), "SymbolResponse list should not be empty.");
		log.info("SymbolLookupResponse: ", symbolResponse);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
