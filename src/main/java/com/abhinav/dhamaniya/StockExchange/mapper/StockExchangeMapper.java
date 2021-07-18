package com.abhinav.dhamaniya.StockExchange.mapper;

import com.abhinav.dhamaniya.StockExchange.dto.StockExchangeDto;
import com.abhinav.dhamaniya.StockExchange.entities.StockExchange;
import org.mapstruct.Mapper;

@Mapper
public interface StockExchangeMapper {
    StockExchange convertToEntity(StockExchangeDto stockExchangeDto);
    StockExchangeDto convertToDto(StockExchange stockExchange);
}
