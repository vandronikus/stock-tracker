package com.luidenterprises.stocktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MetricDTO {
	
	@JsonProperty("10DayAverageTradingVolume")
	private long tenDayAverageTradingVolume;
	@JsonProperty("52WeekHigh")
	private long fiftyTwoWeekHigh;
	@JsonProperty("52WeekLow")
	private long fiftyTwoWeekLow;
	@JsonProperty("52WeekLowDate")
	private String fiftyTwoWeekLowDate;
	private long fiftyTwoWeekPriceReturnDaily;
	private String beta;	
	

}
