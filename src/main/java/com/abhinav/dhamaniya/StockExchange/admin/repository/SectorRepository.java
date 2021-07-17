package com.abhinav.dhamaniya.StockExchange.admin.repository;

import com.abhinav.dhamaniya.StockExchange.admin.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Integer> {
}