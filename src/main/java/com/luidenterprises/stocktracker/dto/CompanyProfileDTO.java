package com.luidenterprises.stocktracker.dto;

import lombok.Data;

@Data
public class CompanyProfileDTO {
	
	private String country;
	private String currency;
	private String exchange;
	private String finnHubIndustry;
	private String ipo;	
	private long marketCapitalization;
	private String name;
	private String phone;
	private long shareOutstanding;
	private String ticker;
	private String webUrl;
	private String logo;	
}