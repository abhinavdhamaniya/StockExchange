package com.abhinav.dhamaniya.StockExchange.admin.service;

import com.abhinav.dhamaniya.StockExchange.admin.dto.StockExchangeDto;
import com.abhinav.dhamaniya.StockExchange.admin.entities.CompanyStockExchangeJoin;
import com.abhinav.dhamaniya.StockExchange.admin.entities.StockExchange;
import com.abhinav.dhamaniya.StockExchange.admin.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.admin.mapper.StockExchangeMapper;
import com.abhinav.dhamaniya.StockExchange.admin.repository.CompanyRepository;
import com.abhinav.dhamaniya.StockExchange.admin.repository.CompanyStockExchangeJoinRepository;
import com.abhinav.dhamaniya.StockExchange.admin.repository.SectorRepository;
import com.abhinav.dhamaniya.StockExchange.admin.repository.StockExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockExchangeService {

    @Autowired
    StockExchangeRepository stockExchangeRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    StockExchangeMapper mapper;
    @Autowired
    CompanyStockExchangeJoinRepository companyStockExchangeJoinRepository;

    @Autowired
    SectorRepository sectorRepository;

    public int createStockExchange(StockExchangeDto stockExchangeDto) throws EntityNotFoundException{
        for(int companyId : stockExchangeDto.getCompanyIds()) {
            if(!companyRepository.findById(companyId).isPresent()) throw new EntityNotFoundException("Company with given ID "+ companyId +" is not found");
        }
        int generatedStockExchangeId = stockExchangeRepository.save(mapper.convertToEntity(stockExchangeDto)).getId();
        for(int companyId : stockExchangeDto.getCompanyIds()) companyStockExchangeJoinRepository.save(new CompanyStockExchangeJoin(0, generatedStockExchangeId, companyId));
        return generatedStockExchangeId;
    }

    public List<StockExchange> getAllStockExchanges() {
        return stockExchangeRepository.findAll();
    }
}
