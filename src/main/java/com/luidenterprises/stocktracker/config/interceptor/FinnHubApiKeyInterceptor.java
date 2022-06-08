package com.luidenterprises.stocktracker.config.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class FinnHubApiKeyInterceptor implements ClientHttpRequestInterceptor {
	
	Logger logger = LoggerFactory.getLogger(FinnHubApiKeyInterceptor.class);
	
	@Value("${finnhub.api.key}")
	private String finnHubApiKey;
	

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {	
	HttpHeaders headers = new HttpHeaders();
	headers.set("X-Finnhub-Secret", finnHubApiKey);
	logger.info("Finnhub secret header set.");
	return execution.execute(request,  body);	
	}
	

	

}
