package com.luidenterprises.stocktracker.dto;

import java.util.List;

import lombok.Data;

@Data
public class SymbolLookupResponse {
	private int count;
	private List<SymbolData> result;
}
