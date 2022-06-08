package com.luidenterprises.stocktracker.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class StockUtils {
	
	
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

}
