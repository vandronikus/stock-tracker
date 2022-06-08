package com.luidenterprises.stocktracker.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.luidenterprises.stocktracker.config.interceptor.FinnHubApiKeyInterceptor;

@Configuration
public class RestTemplateConfiguration {
	
	Logger logger = LoggerFactory.getLogger(RestTemplateConfiguration.class);
	
	/**
	 * Returns a rest-template with the finnhub client interceptor (contains the header secret)
	 * @return
	 */
	@Bean
	RestTemplate restTemplate() {		
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();		
		}		
		interceptors.add(new FinnHubApiKeyInterceptor());		
		restTemplate.setInterceptors(interceptors);
		logger.info("Setting FinnHub interceptor to rest-template");
		
		
		
		return restTemplate;
	}
	

}
