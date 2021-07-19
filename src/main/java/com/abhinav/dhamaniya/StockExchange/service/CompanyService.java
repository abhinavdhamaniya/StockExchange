package com.abhinav.dhamaniya.StockExchange.service;

import com.abhinav.dhamaniya.StockExchange.entities.Company;
import com.abhinav.dhamaniya.StockExchange.entities.CompanyStockExchangeJoin;
import com.abhinav.dhamaniya.StockExchange.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.mapper.CompanyMapper;
import com.abhinav.dhamaniya.StockExchange.repository.CompanyRepository;
import com.abhinav.dhamaniya.StockExchange.repository.CompanyStockExchangeJoinRepository;
import com.abhinav.dhamaniya.StockExchange.repository.StockExchangeRepository;
import com.abhinav.dhamaniya.StockExchange.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyService {
    @Autowired
    StockExchangeRepository stockExchangeRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyStockExchangeJoinRepository companyStockExchangeJoinRepository;
    @Autowired
    CompanyMapper companyMapper;

    public int createCompany(CompanyDto companyDto) throws EntityNotFoundException {

        for(int stockExchangeId : companyDto.getStockExchangeIds()) {
            if(!stockExchangeRepository.findById(stockExchangeId).isPresent()) throw new EntityNotFoundException("Stock Exchange with given ID "+ stockExchangeId +" is not found");
        }
        int generatedCompanyId = companyRepository.save(companyMapper.convertToEntity(companyDto)).getId();
        for(int stockExchangeId : companyDto.getStockExchangeIds()) companyStockExchangeJoinRepository.save(new CompanyStockExchangeJoin(0, stockExchangeId, generatedCompanyId));
        return generatedCompanyId;
    }

    public List<CompanyDto> getAllCompanies() {
        List<CompanyDto> companyDtoList =companyRepository.findAllDeactivatedFalse().stream()
                                            .map(company -> companyMapper.convertToDto(company))
                                            .collect(Collectors.toList());
        for(CompanyDto companyDto: companyDtoList)
        {
            companyDto.setStockExchangeIds(companyStockExchangeJoinRepository.getStockExchangeIdsByCompanyId(companyDto.getId()));
        }
        return companyDtoList;
    }

    public int deactivateCompany(int id) throws EntityNotFoundException {
        Company company = companyRepository.findById(id).orElse(null);
        if(company == null) throw new EntityNotFoundException("Company not found");
        company.setDeactivated(true);
        companyRepository.save(company);
        return company.getId();
    }

    public int updateCompany(CompanyDto companyDto) throws EntityNotFoundException {
        if(!companyRepository.findById(companyDto.getId()).isPresent()) throw new EntityNotFoundException("Company with given ID "+ companyDto.getId() +" is not found");
        for(int stockExchangeId : companyDto.getStockExchangeIds()) {
            if(!stockExchangeRepository.findById(stockExchangeId).isPresent()) throw new EntityNotFoundException("Stock Exchange with given ID "+ stockExchangeId +" is not found");
        }
        int companyId = companyRepository.save(companyMapper.convertToEntity(companyDto)).getId();
        companyStockExchangeJoinRepository.deleteRecordsWithCompanyId(companyId);
        for(int stockExchangeId : companyDto.getStockExchangeIds()) companyStockExchangeJoinRepository.save(new CompanyStockExchangeJoin(0, stockExchangeId, companyId));
        return companyId;
    }
}
