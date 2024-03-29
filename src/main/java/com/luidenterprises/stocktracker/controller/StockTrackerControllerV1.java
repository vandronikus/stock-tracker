 	package com.luidenterprises.stocktracker.controller;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luidenterprises.stocktracker.config.aspect.LogMethodExecution;
import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.BasicFinancialsDTO;
import com.luidenterprises.stocktracker.dto.CompanyProfileDTO;
import com.luidenterprises.stocktracker.dto.QuoteCandlestickDTO;
import com.luidenterprises.stocktracker.dto.QuoteDTO;
import com.luidenterprises.stocktracker.dto.SymbolDataDTO;
import com.luidenterprises.stocktracker.dto.mapper.QuoteCandlestickMapper;
import com.luidenterprises.stocktracker.dto.mapper.QuoteMapper;
import com.luidenterprises.stocktracker.service.StockTrackerService;
import com.luidenterprises.stocktracker.util.StockUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/stocks")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StockTrackerControllerV1 {
	
	
	@Autowired
	StockTrackerService apiService;	
	
	
	@LogMethodExecution	
	@GetMapping(value = "/{symbol}", produces = "application/json;v=1;")	
	public ResponseEntity<QuoteDTO> getSingleStockCurrentPrice(@PathVariable String symbol) {
		Optional<Quote> quoteOptional 		= apiService.getSymbolCurrentPrice(symbol);
		Optional<QuoteDTO> quoteDTOOptional = Optional.ofNullable(QuoteMapper.INSTANCE.quoteToQuoteDTO(quoteOptional.get()));	
		
		return ResponseEntity.of(quoteDTOOptional);
	}
	
	
	@LogMethodExecution
	@GetMapping(value = "/{symbol}/candlestick/{fromDate}/{toDate}", produces = "application/json;v=1;")
	public ResponseEntity<QuoteCandlestickDTO> getSingleStockPriceHistory(@PathVariable String symbol, @PathVariable String fromDate, @PathVariable String toDate) throws Exception {	
		
		Date fromDateObject = StockUtils.getISODate(fromDate);
		Date toDateObject 	= StockUtils.getISODate(toDate);			
		String to 			= StockUtils.toStringUnixTime(toDateObject.toInstant().truncatedTo(ChronoUnit.DAYS));
		String from 		= StockUtils.toStringUnixTime(fromDateObject.toInstant().truncatedTo(ChronoUnit.DAYS));
		
		
		Optional<QuoteCandleStick> quoteCandleStickOptional = apiService.getSymbolHistoricalPrice(symbol, Long.parseLong(from), Long.parseLong(to));	
		Optional<QuoteCandlestickDTO> quoteCandleStickDTOOptional = Optional.ofNullable(QuoteCandlestickMapper.INSTANCE.quoteCandlestickToQuoteCandlestickDTO(quoteCandleStickOptional.get()));
		
		return ResponseEntity.of(quoteCandleStickDTOOptional);		
	}
	
	@LogMethodExecution
	@GetMapping(value = "/{symbol}/data")
	public ResponseEntity<SymbolDataDTO> getSymbolLookupData (@PathVariable String symbol) {
		Optional<SymbolDataDTO> response = apiService.getSymbolLookup(symbol);
		return ResponseEntity.of(response);				
	}
	
	
	@LogMethodExecution
	@GetMapping(value = "/{symbol}/profile")
	public ResponseEntity<CompanyProfileDTO> getCompanyProfile(@PathVariable String symbol) {
		Optional<CompanyProfileDTO> response = apiService.getCompanyProfile(symbol);
		return ResponseEntity.of(response);
	}
	
	
	@LogMethodExecution
	@GetMapping(value ="/{symbol}/financials")
	public ResponseEntity<BasicFinancialsDTO> getBasicFinancials(@PathVariable String symbol) {
		Optional<BasicFinancialsDTO> response = apiService.getBasicFinancials(symbol);
		return ResponseEntity.of(response);
	}
	
	
	
	
	
	
	

}
