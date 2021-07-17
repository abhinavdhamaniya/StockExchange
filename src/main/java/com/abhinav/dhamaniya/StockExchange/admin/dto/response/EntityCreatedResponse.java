package com.abhinav.dhamaniya.StockExchange.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class EntityCreatedResponse {
    int id;
    String message;
}
