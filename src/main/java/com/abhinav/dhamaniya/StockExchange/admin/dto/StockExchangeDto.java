package com.abhinav.dhamaniya.StockExchange.admin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
public class StockExchangeDto {

    private int id;

    private String stockExchangeName;

    private String brief;

    private String contactAddress;

    private String remarks;

    private Set<Integer> companyIds = new HashSet<>();
}
