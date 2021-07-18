package com.abhinav.dhamaniya.StockExchange.admin.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COMPANY")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Company {

    public Company(int id) { this.id = id; }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "TURNOVER")
    private Long turnover;

    @Column(name = "CEO")
    private String ceo;

    @Column(name = "BOARD_OF_DIRECTORS")
    private String boardOfDirectors;

    @Column(name = "LISTED_IN_STOCK_EXCHANGES")
    private boolean listedInStockExchanges;

    @OneToOne
    @JoinColumn(name = "SECTOR_ID", referencedColumnName = "ID")
    private Sector sector;

    @Column(name = "BRIEF")
    private String brief;

    @Column(name = "DEACTIVATED", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean deactivated;
}
