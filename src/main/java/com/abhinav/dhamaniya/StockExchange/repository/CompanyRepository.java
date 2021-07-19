package com.abhinav.dhamaniya.StockExchange.repository;

import com.abhinav.dhamaniya.StockExchange.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(value = "SELECT * FROM COMPANY WHERE DEACTIVATED = FALSE", nativeQuery = true)
    List<Company> findAllDeactivatedFalse();

    @Query(value = "SELECT * FROM COMPANY WHERE DEACTIVATED = FALSE AND ID = :companyId", nativeQuery = true)
    Optional<Company> findByIdDeactivatedFalse(@Param("companyId") int companyId);
}