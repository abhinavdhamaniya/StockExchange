package com.abhinav.dhamaniya.StockExchange.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "STOCK_EXCHANGE")
@Data
@Getter
@Setter
public class StockExchange {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "STOCK_EXCHANGE_NAME")
    private String stockExchangeName;

    @Column(name = "BRIEF")
    private String brief;

    @Column(name = "CONTACT_ADDRESS")
    private String contactAddress;

    @Column(name = "REMARKS")
    private String remarks;
}
