package com.abhinav.dhamaniya.StockExchange.admin.repository;

import com.abhinav.dhamaniya.StockExchange.admin.entities.Ipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpoRepository extends JpaRepository<Ipo, Integer> {
}