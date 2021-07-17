package com.abhinav.dhamaniya.StockExchange.admin.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "IPO")
@Data
@Getter
@Setter
public class Ipo {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @OneToOne
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
    private Company company;

    @Column(name = "PRICE_PER_SHARE")
    private Double pricePerShare;

    @Column(name = "TOTAL_SHARES")
    private Long totalShares;

    @Column(name = "OPEN_DATE_TIME")
    private LocalDateTime openDateTime;

    @Column(name = "REMARKS")
    private String remarks;
}
