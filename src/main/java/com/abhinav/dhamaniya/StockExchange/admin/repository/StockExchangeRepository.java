package com.abhinav.dhamaniya.StockExchange.admin.repository;

import com.abhinav.dhamaniya.StockExchange.admin.entities.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer> {
}