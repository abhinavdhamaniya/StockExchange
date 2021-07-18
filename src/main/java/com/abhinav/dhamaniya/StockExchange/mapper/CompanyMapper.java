package com.abhinav.dhamaniya.StockExchange.mapper;

import com.abhinav.dhamaniya.StockExchange.entities.Company;
import com.abhinav.dhamaniya.StockExchange.dto.CompanyDto;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {
    Company convertToEntity(CompanyDto companyDto);
    CompanyDto convertToDto(Company company);
}