package com.luidenterprises.stocktracker.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ReactiveHttpConfig {
	
	@Value("${http.read.timeout.milliseconds}")
	private int httpReadTimeout;
	@Value("${http.write.timeout.milliseconds}")
	private int httpWriteTimeout;
	@Value("${http.connect.timeout.milliseconds}")
	private int httpConnectTimeout;
	
	@Bean
	@Scope("singleton")
	public HttpClient getHttpClient() {
		HttpClient httpClient = HttpClient.create()
				  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, httpConnectTimeout)
				  .responseTimeout(Duration.ofMillis(httpConnectTimeout))
				  .doOnConnected(conn -> 
				  conn.addHandlerLast(new ReadTimeoutHandler(httpReadTimeout, TimeUnit.MILLISECONDS))
				  .addHandlerLast(new WriteTimeoutHandler(httpWriteTimeout, TimeUnit.MILLISECONDS)));
		return httpClient;
	}
	
	
	
	


}
