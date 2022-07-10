package com.luidenterprises.stocktracker.dto;

import lombok.Data;

@Data
public class BasicFinancialsDTO {
	
	private SeriesDTO series;
	private MetricDTO metric;
	private String metricType;
	private String symbol;

}
