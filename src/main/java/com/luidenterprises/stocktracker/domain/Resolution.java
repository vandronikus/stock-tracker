package com.luidenterprises.stocktracker.domain;

public enum Resolution {
	ONE("1"),
	FIVE("5"),
	FIFTEEN("15"),
	THIRTY("30"),
	SIXTY("60"),
	DAY("D"),
	WEEK("W"),
	MONTH("M");
	
	private String resolutionCode;

	private Resolution(String resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	
	public String getResolutionCode() {
		return this.resolutionCode;
	}
	
	

}
