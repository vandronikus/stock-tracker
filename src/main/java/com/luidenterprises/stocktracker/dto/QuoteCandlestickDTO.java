package com.luidenterprises.stocktracker.dto;

import java.util.List;

import com.luidenterprises.stocktracker.domain.Resolution;

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
	
	
	
	public Resolution getResolution() {
		return resolution;
	}
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	public long getFromUnixTimeStamp() {
		return fromUnixTimeStamp;
	}
	public void setFromUnixTimeStamp(long fromUnixTimeStamp) {
		this.fromUnixTimeStamp = fromUnixTimeStamp;
	}
	public long getToUnixTimeStamp() {
		return toUnixTimeStamp;
	}
	public void setToUnixTimeStamp(long toUnixTimeStamp) {
		this.toUnixTimeStamp = toUnixTimeStamp;
	}
	public boolean isAdjusted() {
		return adjusted;
	}
	public void setAdjusted(boolean adjusted) {
		this.adjusted = adjusted;
	}
	public List<Double> getOpen() {
		return open;
	}
	public void setOpen(List<Double> open) {
		this.open = open;
	}
	public List<Double> getHigh() {
		return high;
	}
	public void setHigh(List<Double> high) {
		this.high = high;
	}
	public List<Double> getLow() {
		return low;
	}
	public void setLow(List<Double> low) {
		this.low = low;
	}
	public List<Double> getClose() {
		return close;
	}
	public void setClose(List<Double> close) {
		this.close = close;
	}
	public List<Long> getVolume() {
		return volume;
	}
	public void setVolume(List<Long> volume) {
		this.volume = volume;
	}
	public List<Long> getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(List<Long> timestamps) {
		this.timestamps = timestamps;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	
	

}
