package com.abhinav.dhamaniya.StockExchange.repository;

import com.abhinav.dhamaniya.StockExchange.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(value = "SELECT * FROM COMPANY WHERE DEACTIVATED = FALSE", nativeQuery = true)
    List<Company> findAllDeactivatedFalse();
}