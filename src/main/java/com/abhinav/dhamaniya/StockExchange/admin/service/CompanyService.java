package com.abhinav.dhamaniya.StockExchange.admin.service;

import com.abhinav.dhamaniya.StockExchange.admin.entities.Company;
import com.abhinav.dhamaniya.StockExchange.admin.entities.CompanyStockExchangeJoin;
import com.abhinav.dhamaniya.StockExchange.admin.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.admin.mapper.CompanyMapper;
import com.abhinav.dhamaniya.StockExchange.admin.repository.CompanyRepository;
import com.abhinav.dhamaniya.StockExchange.admin.repository.CompanyStockExchangeJoinRepository;
import com.abhinav.dhamaniya.StockExchange.admin.repository.StockExchangeRepository;
import com.abhinav.dhamaniya.StockExchange.admin.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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
    CompanyMapper mapper;

    public int createCompany(CompanyDto companyDto) throws EntityNotFoundException {

        for(int stockExchangeId : companyDto.getStockExchangeIds()) {
            if(!stockExchangeRepository.findById(stockExchangeId).isPresent()) throw new EntityNotFoundException("Stock Exchange with given ID "+ stockExchangeId +" is not found");
        }
        int generatedCompanyId = companyRepository.save(mapper.convertToEntity(companyDto)).getId();
        for(int stockExchangeId : companyDto.getStockExchangeIds()) companyStockExchangeJoinRepository.save(new CompanyStockExchangeJoin(0, stockExchangeId, generatedCompanyId));
        return generatedCompanyId;
    }

    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAllDeactivatedFalse().stream().map(company -> mapper.convertToDto(company)).collect(Collectors.toList());
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
        int companyId = companyRepository.save(mapper.convertToEntity(companyDto)).getId();
        companyStockExchangeJoinRepository.deleteRecordsWithCompanyId(companyId);
        for(int stockExchangeId : companyDto.getStockExchangeIds()) companyStockExchangeJoinRepository.save(new CompanyStockExchangeJoin(0, stockExchangeId, companyId));
        return companyId;
    }
}
