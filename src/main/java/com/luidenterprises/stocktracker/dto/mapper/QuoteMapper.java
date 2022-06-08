package com.luidenterprises.stocktracker.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.luidenterprises.stocktracker.domain.Quote;
import com.luidenterprises.stocktracker.dto.QuoteDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface QuoteMapper {
	
	QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);
	
	@Mapping(source = "o", target="open")
	@Mapping(source = "h", target="high")
	@Mapping(source = "l", target="low")
	@Mapping(source = "c", target="current")
	@Mapping(source = "p", target="previous")
	QuoteDTO quoteToQuoteDTO(Quote quote);
	
	
	

}
