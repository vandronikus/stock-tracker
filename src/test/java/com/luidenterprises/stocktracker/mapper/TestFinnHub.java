package com.luidenterprises.stocktracker.mapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.service.FinnHubApiService;
import com.luidenterprises.stocktracker.util.StockUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TestFinnHub {
	
	@Autowired
	FinnHubApiService apiService;
	
	@Test
	public void testFinnHubHistorialSymbolRequest() {		
		
		String to = StockUtils.toStringUnixTime(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.ofHours(-4)));
		String from = StockUtils.toStringUnixTime(LocalDate.now().atStartOfDay().minus(10, ChronoUnit.DAYS).toInstant(ZoneOffset.ofHours(-4)));
		
		Optional<QuoteCandleStick> quoteList = apiService.getSymbolHistoricalPrice("TSLA", Long.parseLong(from), Long.parseLong(to));
		log.info("quoteList: ", quoteList.get().toString());
		Assert.notNull(quoteList.get(), "quoteList is not null.");		
	}
	


}
