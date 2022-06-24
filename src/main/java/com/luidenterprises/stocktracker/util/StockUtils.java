package com.luidenterprises.stocktracker.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StockUtils {		
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Converts a string to Unix time format
	 * @param temporal
	 * @return
	 */
	public static String toStringUnixTime(TemporalAccessor temporal) {
		  Instant i = Instant.from(temporal);
		  BigDecimal nanos = BigDecimal.valueOf(i.getNano(), 9);
		  BigDecimal seconds = BigDecimal.valueOf(i.getEpochSecond());
		  BigDecimal total = seconds.add(nanos);
		  DecimalFormat df = new DecimalFormat("#.#########");
		  return df.format(total);
		}
	
	/**
	 * Returns a Date object from a parsed iso-8601 String
	 * @param isoDate
	 * @return
	 * @throws Exception
	 */
	public static Date getISODate(String isoDate) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(isoDate);	
	}
	
	/**
	 * Returns a default object writer
	 * @return
	 */
	public static ObjectWriter getObjectWriter() {		
		ObjectWriter writer = objectMapper
								  .writer()
								  .withDefaultPrettyPrinter();
		return writer;		
	}
	
	
	
	
	

}
