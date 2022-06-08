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

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.QuoteDTO;
import com.luidenterprises.stocktracker.dto.mapper.QuoteMapper;
import com.luidenterprises.stocktracker.service.ApiService;
import com.luidenterprises.stocktracker.util.StockUtils;

@RestController
@RequestMapping("/stocks")
public class StockTrackerController {
	
	
	@Autowired
	ApiService apiService;
	
	@GetMapping(value = "/{symbol}", produces = "application/json;v=1;")
	public ResponseEntity<QuoteDTO> getSingleStockCurrentPrice(@PathVariable String symbol) {
		Optional<Quote> quoteOptional 		= apiService.getSymbolCurrentPrice(symbol);
		Optional<QuoteDTO> quoteDTOOptional = Optional.ofNullable(QuoteMapper.INSTANCE.quoteToQuoteDTO(quoteOptional.get()));
		return ResponseEntity.of(quoteDTOOptional);
	}
	
	
	
	@GetMapping(value = "/{symbol}/candlestick/{fromDate}/{toDate}", produces = "application/json;v=1;")
	public ResponseEntity<QuoteCandleStick> getSingleStockPriceHistory(@PathVariable String symbol, @PathVariable String fromDate, @PathVariable String toDate) throws Exception {
		
		
		Date fromDateObject = StockUtils.getISODate(fromDate);
		Date toDateObject 	= StockUtils.getISODate(toDate);
		
		
		String to = StockUtils.toStringUnixTime(toDateObject.toInstant().truncatedTo(ChronoUnit.DAYS));
		String from = StockUtils.toStringUnixTime(fromDateObject.toInstant().truncatedTo(ChronoUnit.DAYS));
		
		
		Optional<QuoteCandleStick> quoteCandleStick = apiService.getSymbolHistoricalPrice("TSLA", Long.parseLong(from), Long.parseLong(to));	
		
		//Optional<List<Quote>> listQuoteDTOOptional =
		return ResponseEntity.of(quoteCandleStick);
		
	}
	
	
	

}
