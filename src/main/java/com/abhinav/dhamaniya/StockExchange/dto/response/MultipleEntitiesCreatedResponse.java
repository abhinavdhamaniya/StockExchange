package com.abhinav.dhamaniya.StockExchange.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class MultipleEntitiesCreatedResponse {
    List<Integer> id;
    String message;
}
