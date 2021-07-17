package com.abhinav.dhamaniya.StockExchange.admin.service;

import com.abhinav.dhamaniya.StockExchange.admin.entities.StockExchange;
import com.abhinav.dhamaniya.StockExchange.admin.repository.CompanyRepository;
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
    SectorRepository sectorRepository;

    public StockExchange createStockExchange(StockExchange stockExchange) {
        return stockExchangeRepository.save(stockExchange);
    }

    public List<StockExchange> getAllStockExchanges() {
        return stockExchangeRepository.findAll();
    }
}
