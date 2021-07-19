package com.abhinav.dhamaniya.StockExchange.service;

import com.abhinav.dhamaniya.StockExchange.dto.StockExchangeDto;
import com.abhinav.dhamaniya.StockExchange.entities.CompanyStockExchangeJoin;
import com.abhinav.dhamaniya.StockExchange.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.mapper.StockExchangeMapper;
import com.abhinav.dhamaniya.StockExchange.repository.CompanyRepository;
import com.abhinav.dhamaniya.StockExchange.repository.CompanyStockExchangeJoinRepository;
import com.abhinav.dhamaniya.StockExchange.repository.SectorRepository;
import com.abhinav.dhamaniya.StockExchange.repository.StockExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockExchangeService {

    @Autowired
    StockExchangeRepository stockExchangeRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    StockExchangeMapper stockExchangeMapper;
    @Autowired
    CompanyStockExchangeJoinRepository companyStockExchangeJoinRepository;

    public int createStockExchange(StockExchangeDto stockExchangeDto) throws EntityNotFoundException{
        for(int companyId : stockExchangeDto.getCompanyIds()) {
            if(!companyRepository.findById(companyId).isPresent()) throw new EntityNotFoundException("Company with given ID "+ companyId +" is not found");
        }
        int generatedStockExchangeId = stockExchangeRepository.save(stockExchangeMapper.convertToEntity(stockExchangeDto)).getId();
        for(int companyId : stockExchangeDto.getCompanyIds()) companyStockExchangeJoinRepository.save(new CompanyStockExchangeJoin(0, generatedStockExchangeId, companyId));
        return generatedStockExchangeId;
    }

    public List<StockExchangeDto> getAllStockExchanges() {
        List<StockExchangeDto> stockExchangeDtoList =  stockExchangeRepository.findAll()
                                                            .stream()
                                                            .map(stockExchange -> stockExchangeMapper.convertToDto(stockExchange)).collect(Collectors.toList());
        for(StockExchangeDto stockExchangeDto: stockExchangeDtoList)
        {
            stockExchangeDto.setCompanyIds(companyStockExchangeJoinRepository.getCompanyIdsByStockExchangeId(stockExchangeDto.getId()));
        }
        return stockExchangeDtoList;
    }
}
