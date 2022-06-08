package com.luidenterprises.stocktracker.dto;

public class QuoteDTO {
	
	private String open;
	private String high;
	private String low;
	private String current;
	private String previous;
	
	
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuoteDTO [open=").append(open).append(", high=").append(high).append(", low=").append(low)
				.append(", current=").append(current).append(", previous=").append(previous).append("]");
		return builder.toString();
	}
	
	
	
	
	

}
