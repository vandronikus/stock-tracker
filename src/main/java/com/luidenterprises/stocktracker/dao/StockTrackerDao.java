package com.luidenterprises.stocktracker.dao;

import org.springframework.http.ResponseEntity;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.BasicFinancialsDTO;
import com.luidenterprises.stocktracker.dto.CompanyProfileDTO;
import com.luidenterprises.stocktracker.dto.SymbolLookupResponseDTO;

public interface StockTrackerDao {
	
	public ResponseEntity<Quote> getSymbolCurrentPrice(String symbol);
	public ResponseEntity<QuoteCandleStick> getSymbolHistoricalPrice(String symbol, long fromUnixTimestamp, long toUnixTimeStamp);
	public ResponseEntity<SymbolLookupResponseDTO> getSymbolLookup(String symbol);
	public ResponseEntity<CompanyProfileDTO> getCompanyProfile(String symbol);
	public ResponseEntity<BasicFinancialsDTO> getBasicFinancials(String symbol);
	
	
}
