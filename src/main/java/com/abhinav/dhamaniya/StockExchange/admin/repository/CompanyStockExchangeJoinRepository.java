package com.abhinav.dhamaniya.StockExchange.admin.repository;

import com.abhinav.dhamaniya.StockExchange.admin.entities.CompanyStockExchangeJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CompanyStockExchangeJoinRepository extends JpaRepository<CompanyStockExchangeJoin, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM COMPANY_STOCK_EXCHANGE_JOIN WHERE COMPANY_ID = :companyId", nativeQuery = true)
    void deleteRecordsWithCompanyId(@Param("companyId") int companyId);
}