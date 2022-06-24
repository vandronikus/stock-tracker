package com.luidenterprises.stocktracker.util;

import java.time.Duration;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

public interface StockConstants {
	
	HttpClient httpClient = HttpClient.create()
			  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			  .responseTimeout(Duration.ofMillis(5000))
			  .doOnConnected(conn -> 
			    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
			      .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
	
	public static final String FINNHUB_XAPI_KEY_HEADER 		=  "X-Finnhub-Token";	
	public static final DateTimeFormatter DATE_FORMATTER 	= DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
            														   	   .withLocale( Locale.ENGLISH)
            														   	   .withZone( ZoneId.systemDefault() );
	
	
	

}
