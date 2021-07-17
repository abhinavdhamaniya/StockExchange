package com.abhinav.dhamaniya.StockExchange.admin.mapper;

import com.abhinav.dhamaniya.StockExchange.admin.entities.Company;
import com.abhinav.dhamaniya.StockExchange.admin.dto.CompanyDto;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {
    Company convertToEntity(CompanyDto companyDto);
    CompanyDto convertToDto(Company company);
}