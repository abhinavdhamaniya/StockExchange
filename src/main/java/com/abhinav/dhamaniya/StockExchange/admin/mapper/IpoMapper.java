package com.abhinav.dhamaniya.StockExchange.admin.mapper;

import com.abhinav.dhamaniya.StockExchange.admin.dto.IpoDto;
import com.abhinav.dhamaniya.StockExchange.admin.entities.Ipo;
import org.mapstruct.Mapper;

@Mapper
public interface IpoMapper {
    Ipo convertToEntity(IpoDto ipoDto);
    IpoDto convertToDto(Ipo ipo);
}
