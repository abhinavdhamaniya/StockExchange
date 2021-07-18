package com.abhinav.dhamaniya.StockExchange.mapper;

import com.abhinav.dhamaniya.StockExchange.dto.IpoDto;
import com.abhinav.dhamaniya.StockExchange.entities.Ipo;
import org.mapstruct.Mapper;

@Mapper
public interface IpoMapper {
    Ipo convertToEntity(IpoDto ipoDto);
    IpoDto convertToDto(Ipo ipo);
}
