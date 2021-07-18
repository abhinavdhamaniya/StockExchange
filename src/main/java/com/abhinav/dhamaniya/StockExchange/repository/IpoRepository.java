package com.abhinav.dhamaniya.StockExchange.repository;

import com.abhinav.dhamaniya.StockExchange.entities.Ipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpoRepository extends JpaRepository<Ipo, Integer> {
}