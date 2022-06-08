package com.luidenterprises.stocktracker.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteCandleStick {
	
	private Resolution resolution;
	private long fromUnixTimeStamp;
	private long toUnixTimeStamp;
	private boolean adjusted;
	
	
	/**
	 * List of open prices
	 */
	private List<Double> o;
	/**
	 * List of high prices
	 */
	private List<Double> h;
	/**
	 * List of low prices
	 */
	private List<Double> l;
	/**
	 * List of closed prices	
	 */
	private List<Double> c;
	/**
	 * List of volume data
	 */	
	private List<Long> v;
	/**
	 * List of timestamps in unix format
	 */
	private List<Long> t;
	/**
	 * Status of response
	 */
	private String s;
	
	
	
	
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
	public List<Double> getO() {
		return o;
	}
	public void setO(List<Double> o) {
		this.o = o;
	}
	public List<Double> getH() {
		return h;
	}
	public void setH(List<Double> h) {
		this.h = h;
	}
	public List<Double> getL() {
		return l;
	}
	public void setL(List<Double> l) {
		this.l = l;
	}
	public List<Double> getC() {
		return c;
	}
	public void setC(List<Double> c) {
		this.c = c;
	}
	public List<Long> getV() {
		return v;
	}
	public void setV(List<Long> v) {
		this.v = v;
	}
	public List<Long> getT() {
		return t;
	}
	public void setT(List<Long> t) {
		this.t = t;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuoteCandleStick [resolution=").append(resolution).append(", fromUnixTimeStamp=")
				.append(fromUnixTimeStamp).append(", toUnixTimeStamp=").append(toUnixTimeStamp).append(", adjusted=")
				.append(adjusted).append(", o=").append(o).append(", h=").append(h).append(", l=").append(l)
				.append(", c=").append(c).append(", v=").append(v).append(", t=").append(t).append(", s=").append(s)
				.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
