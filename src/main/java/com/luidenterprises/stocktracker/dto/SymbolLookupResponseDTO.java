package com.luidenterprises.stocktracker.dto;

import java.util.List;

import lombok.Data;

@Data
public class SymbolLookupResponseDTO {
	private int count;
	private List<SymbolDataDTO> result;
}
