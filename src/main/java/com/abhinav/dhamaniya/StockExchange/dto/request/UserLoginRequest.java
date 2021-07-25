package com.abhinav.dhamaniya.StockExchange.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserLoginRequest {
    private String username;
    private String password;
}
