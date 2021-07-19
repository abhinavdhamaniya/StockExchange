package com.abhinav.dhamaniya.StockExchange.repository;

import com.abhinav.dhamaniya.StockExchange.entities.CompanyStockExchangeJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Set;

public interface CompanyStockExchangeJoinRepository extends JpaRepository<CompanyStockExchangeJoin, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM COMPANY_STOCK_EXCHANGE_JOIN WHERE COMPANY_ID = :companyId", nativeQuery = true)
    void deleteRecordsWithCompanyId(@Param("companyId") int companyId);

    @Query(value = "SELECT STOCK_EXCHANGE_ID FROM COMPANY_STOCK_EXCHANGE_JOIN WHERE COMPANY_ID = :companyId", nativeQuery = true)
    Set<Integer> getStockExchangeIdsByCompanyId(@Param("companyId") int companyId);
}