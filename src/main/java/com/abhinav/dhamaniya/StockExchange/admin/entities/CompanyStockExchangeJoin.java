package com.abhinav.dhamaniya.StockExchange.admin.entities;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "COMPANY_STOCK_EXCHANGE_JOIN")
@AllArgsConstructor
public class CompanyStockExchangeJoin {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "STOCK_EXCHANGE_ID")
    private int stockExchangeId;

    @Column(name = "COMPANY_ID")
    private int companyId;
}
