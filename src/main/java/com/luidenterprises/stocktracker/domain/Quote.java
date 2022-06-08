package com.luidenterprises.stocktracker.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	
	/**
	 * Open price
	 */
	private BigDecimal o;
	/**
	 * High price
	 */
	private BigDecimal h;
	/**
	 * Low price
	 */
	private BigDecimal l;
	/**
	 * Current price
	 */
	private BigDecimal c;
	/**
	 * Previous Day's close
	 */
	private BigDecimal p;	
	

	public BigDecimal getO() {
		return o;
	}

	public void setO(BigDecimal o) {
		this.o = o;
	}

	public BigDecimal getH() {
		return h;
	}

	public void setH(BigDecimal h) {
		this.h = h;
	}

	public BigDecimal getL() {
		return l;
	}

	public void setL(BigDecimal l) {
		this.l = l;
	}

	public BigDecimal getC() {
		return c;
	}

	public void setC(BigDecimal c) {
		this.c = c;
	}

	public BigDecimal getP() {
		return p;
	}

	public void setP(BigDecimal p) {
		this.p = p;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quote [o=").append(o).append(", h=").append(h).append(", l=").append(l).append(", c=").append(c)
				.append(", p=").append(p).append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	

	
	

}
