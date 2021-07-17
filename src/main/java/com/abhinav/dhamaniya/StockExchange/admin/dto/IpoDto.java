package com.abhinav.dhamaniya.StockExchange.admin.dto;

import com.abhinav.dhamaniya.StockExchange.admin.entities.Company;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class IpoDto {
    private int id;

    private Company company;

    private String stockExchangeName;

    private Double pricePerShare;

    private Long totalShares;

    private LocalDateTime openDateTime;

    private String remarks;
}
