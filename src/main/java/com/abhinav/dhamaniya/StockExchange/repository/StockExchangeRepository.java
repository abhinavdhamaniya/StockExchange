package com.abhinav.dhamaniya.StockExchange.repository;

import com.abhinav.dhamaniya.StockExchange.entities.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer> {
}