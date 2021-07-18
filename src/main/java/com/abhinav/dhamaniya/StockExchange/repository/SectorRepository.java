package com.abhinav.dhamaniya.StockExchange.repository;

import com.abhinav.dhamaniya.StockExchange.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Integer> {
}