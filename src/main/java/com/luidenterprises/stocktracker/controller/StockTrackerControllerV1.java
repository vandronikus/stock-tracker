 	package com.luidenterprises.stocktracker.controller;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luidenterprises.stocktracker.config.aspect.LogMethodExecution;
import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.QuoteCandlestickDTO;
import com.luidenterprises.stocktracker.dto.QuoteDTO;
import com.luidenterprises.stocktracker.dto.mapper.QuoteCandlestickMapper;
import com.luidenterprises.stocktracker.dto.mapper.QuoteMapper;
import com.luidenterprises.stocktracker.service.StockTrackerService;
import com.luidenterprises.stocktracker.util.StockUtils;

@RestController
@RequestMapping("/v1/stocks")
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
		
		String to = StockUtils.toStringUnixTime(toDateObject.toInstant().truncatedTo(ChronoUnit.DAYS));
		String from = StockUtils.toStringUnixTime(fromDateObject.toInstant().truncatedTo(ChronoUnit.DAYS));
		
		
		Optional<QuoteCandleStick> quoteCandleStickOptional = apiService.getSymbolHistoricalPrice("TSLA", Long.parseLong(from), Long.parseLong(to));	
		Optional<QuoteCandlestickDTO> quoteCandleStickDTOOptional = Optional.ofNullable(QuoteCandlestickMapper.INSTANCE.quoteCandlestickToQuoteCandlestickDTO(quoteCandleStickOptional.get()));
		
		return ResponseEntity.of(quoteCandleStickDTOOptional);		
	}
	
	
	
	
	
	
	

}
