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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
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
        List<CompanyDto> companyDtoList = companyRepository.findAllDeactivatedFalse().stream()
                                            .map(company -> companyMapper.convertToDto(company))
                                            .collect(Collectors.toList());
        for(CompanyDto companyDto: companyDtoList)
        {
            companyDto.setStockExchangeIds(companyStockExchangeJoinRepository.getStockExchangeIdsByCompanyId(companyDto.getId()));
        }
        return companyDtoList;
    }

    public CompanyDto getCompanyById(@PathVariable int id) throws EntityNotFoundException {
        CompanyDto companyDto = companyMapper.convertToDto(companyRepository.findByIdDeactivatedFalse(id).orElse(null));
        if(companyDto == null) throw new EntityNotFoundException("Company with id: "+id+" not found.");
        companyDto.setStockExchangeIds(companyStockExchangeJoinRepository.getStockExchangeIdsByCompanyId(companyDto.getId()));
        return companyDto;
    }

    public List<CompanyDto> getCompanyListByCompanyIdList(List<Integer> companyIdList) {

        List<CompanyDto> companyDtoList = new ArrayList<>();
        companyIdList.forEach(companyId -> {
            try {
                CompanyDto companyDto = companyMapper.convertToDto(companyRepository.findByIdDeactivatedFalse(companyId).orElse(null));
                if (companyDto == null)
                    throw new EntityNotFoundException("Company with id: " + companyId + " not found.");
                companyDto.setStockExchangeIds(companyStockExchangeJoinRepository.getStockExchangeIdsByCompanyId(companyDto.getId()));
                companyDtoList.add(companyDto);
            }
            catch (EntityNotFoundException entityNotFoundException)
            {
                System.out.println(entityNotFoundException);
            }
        });
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

    public boolean checkCompanyExists(int id)
    {
        if(companyRepository.findById(id).isPresent()) return true;
        return false;
    }
}
