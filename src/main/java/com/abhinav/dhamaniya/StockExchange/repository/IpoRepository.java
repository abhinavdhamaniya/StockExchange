package com.abhinav.dhamaniya.StockExchange.repository;

import com.abhinav.dhamaniya.StockExchange.entities.Ipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IpoRepository extends JpaRepository<Ipo, Integer> {

    @Query(value = "SELECT * FROM IPO WHERE COMPANY_ID = :companyId", nativeQuery = true)
    List<Ipo> findByCompanyId(@Param("companyId") int companyId);

}