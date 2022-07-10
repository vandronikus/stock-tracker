package com.luidenterprises.stocktracker;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.BasicFinancialsDTO;
import com.luidenterprises.stocktracker.dto.CompanyProfileDTO;
import com.luidenterprises.stocktracker.dto.SymbolDataDTO;
import com.luidenterprises.stocktracker.service.StockTrackerService;
import com.luidenterprises.stocktracker.util.StockUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
public class StockTrackerTest {
	
	@Autowired
	StockTrackerService apiService;
	
	@Test
	public void testFinnHubHistorialSymbolRequestApi() {		
		String to = StockUtils.toStringUnixTime(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.ofHours(-4)));
		String from = StockUtils.toStringUnixTime(LocalDate.now().atStartOfDay().minus(10, ChronoUnit.DAYS).toInstant(ZoneOffset.ofHours(-4)));
		
		Optional<QuoteCandleStick> quoteList = apiService.getSymbolHistoricalPrice("TSLA", Long.parseLong(from), Long.parseLong(to));		
		Assert.isTrue(quoteList.isPresent(), "quoteList is not null and present.");		
		System.out.println("quoteList: " + quoteList.get().toString());
	}
	
	@Test
	public void testFinnHubCurrentPriceApi() {
		Optional<Quote> quoteResponse = apiService.getSymbolCurrentPrice("TSLA");		
		Assert.notNull(quoteResponse.get(), "quoteResponse is not null.");	
		System.out.println("quoteResponse: " + quoteResponse.get().toString());
	}
	
	
	@Test
	public void testFinnHubSymbolLookup() {
		Optional<SymbolDataDTO> symbolData = apiService.getSymbolLookup("TSLA");
		System.out.println("SymbolLookupResponse: " + symbolData);	
		Assert.notNull(symbolData.get(), "SymbolLookupResponse should not be null");
		Assert.notNull(symbolData.get().getDescription(), "SymbolResponse description list should not be empty.");
			
	}
	
	
	@Test
	public void testFinnHubCompanyProfile() {
		Optional<CompanyProfileDTO> companyProfile = apiService.getCompanyProfile("TSLA");
		System.out.println("Company Profile: " + companyProfile);
		Assert.notNull(companyProfile.get(), "Company Profile should not be null");
		Assert.notNull(companyProfile.get().getName(), "Company Profile name should not be null");
	}
	
	
	@Test
	public void testBasicFinacials() {
		Optional<BasicFinancialsDTO> basicFinancials = apiService.getBasicFinancials("TSLA");
		System.out.println("Basic Financials: " + basicFinancials);
		Assert.notNull(basicFinancials.get(), "Basic Financials should not be null");
		Assert.notNull(basicFinancials.get().getSymbol(), "Basic Financials symbol should not be null");
	}
}
