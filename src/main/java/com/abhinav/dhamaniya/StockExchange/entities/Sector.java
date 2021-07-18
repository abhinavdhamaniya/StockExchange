package com.abhinav.dhamaniya.StockExchange.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SECTOR")
@Data
@Getter
@Setter
public class Sector {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "SECTOR_NAME")
    private String sectorName;

    @Column(name = "BRIEF")
    private String brief;
}
