package com.abhinav.dhamaniya.StockExchange.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class EntityNotFoundResponse {
    String message;
}