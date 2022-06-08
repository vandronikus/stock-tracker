package com.luidenterprises.stocktracker.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.luidenterprises.stocktracker.domain.QuoteCandleStick;
import com.luidenterprises.stocktracker.dto.QuoteCandlestickDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface QuoteCandlestickMapper {
	
	QuoteCandlestickMapper INSTANCE = Mappers.getMapper(QuoteCandlestickMapper.class);
	
	
	@Mapping(source = "o", target="open")
	@Mapping(source = "h", target="high")
	@Mapping(source = "l", target="low")
	@Mapping(source = "v", target="volume")
	@Mapping(source = "t", target="timestamps")
	@Mapping(source = "s", target="responseStatus")
	QuoteCandlestickDTO quoteCandlestickToQuoteCandlestickDTO(QuoteCandleStick quote);
	

}
