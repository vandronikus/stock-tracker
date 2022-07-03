package com.luidenterprises.stocktracker.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.QuoteCandlestickDTO;
import com.luidenterprises.stocktracker.dto.QuoteDTO;
import com.luidenterprises.stocktracker.dto.mapper.QuoteCandlestickMapper;
import com.luidenterprises.stocktracker.dto.mapper.QuoteMapper;


@SpringBootTest
public class TestMapStruct {
	
	@Autowired
	QuoteMapper quoteMapper;
	@Autowired
	QuoteCandlestickMapper quoteCandlestickMapper;
	
	
	
	@Test
	public void shouldMapQuoteToQuoteDTO() {
		Quote quote = new Quote();
		quote.setC(new BigDecimal(5.00));
		quote.setH(new BigDecimal(20.00));
		quote.setL(new BigDecimal(1.00));
		quote.setO(new BigDecimal(2.00));
		quote.setP(  new BigDecimal(3.00));
		
		QuoteDTO quoteDto = quoteMapper.quoteToQuoteDTO(quote);
		Assert.notNull(quoteDto, "quoteDto is not null");		
	}
	
	
	
	
	@Test
	public void shouldMapQuoteCandleStickToQuoteCandlestickDTO() {
		QuoteCandleStick quoteCandleStick = new QuoteCandleStick();
		quoteCandleStick.setAdjusted(true);
		quoteCandleStick.setC(new ArrayList<Double>());
		quoteCandleStick.setH(new ArrayList<Double>());
		quoteCandleStick.setL(new ArrayList<Double>());
		quoteCandleStick.setO(new ArrayList<Double>());
		quoteCandleStick.setFromUnixTimeStamp(new Date().getTime());
		quoteCandleStick.setToUnixTimeStamp(new Date().getTime());		
		
		QuoteCandlestickDTO quoteCandleStickDTO = quoteCandlestickMapper.quoteCandlestickToQuoteCandlestickDTO(quoteCandleStick);
		Assert.notNull(quoteCandleStickDTO, "quoteCandlestick is not null");
		
		
	}

}
