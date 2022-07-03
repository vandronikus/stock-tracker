package com.luidenterprises.stocktracker.dto;

import java.util.List;

import com.luidenterprises.stocktracker.domain.Resolution;

import lombok.Data;
@Data
public class QuoteCandlestickDTO {
	
	private Resolution resolution;
	private long fromUnixTimeStamp;
	private long toUnixTimeStamp;
	private boolean adjusted;	
	
	/**
	 * List of open prices
	 */
	private List<Double> open;
	/**
	 * List of high prices
	 */
	private List<Double> high;
	/**
	 * List of low prices
	 */
	private List<Double> low;
	/**
	 * List of closed prices	
	 */
	private List<Double> close;
	/**
	 * List of volume data
	 */	
	private List<Long> volume;
	/**
	 * List of timestamps in unix format
	 */
	private List<Long> timestamps;
	/**
	 * Status of response
	 */
	private String responseStatus;
	
	
	
	
	
	
	

}
