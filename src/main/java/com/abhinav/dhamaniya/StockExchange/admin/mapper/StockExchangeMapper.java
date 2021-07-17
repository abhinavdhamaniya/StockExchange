package com.abhinav.dhamaniya.StockExchange.admin.mapper;

import com.abhinav.dhamaniya.StockExchange.admin.dto.StockExchangeDto;
import com.abhinav.dhamaniya.StockExchange.admin.entities.StockExchange;
import org.mapstruct.Mapper;

@Mapper
public interface StockExchangeMapper {
    StockExchange convertToEntity(StockExchangeDto stockExchangeDto);
    StockExchangeDto convertToDto(StockExchange stockExchange);
}
