package com.abhinav.dhamaniya.StockExchange.admin.dto;

import com.abhinav.dhamaniya.StockExchange.admin.entities.Sector;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
public class CompanyDto {

    private int id;

    private String companyName;

    private Long turnover;

    private String ceo;

    private String boardOfDirectors;

    private boolean listedInStockExchanges;

    private Sector sector;

    private String brief;

    private Boolean deactivated= false;

    private Set<Integer> stockExchangeIds = new HashSet<>();
}
