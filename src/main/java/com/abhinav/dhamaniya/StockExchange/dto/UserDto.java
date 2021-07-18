package com.abhinav.dhamaniya.StockExchange.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
    private String username;

    private String password;

    private String userType;

    private String email;

    private String phoneNumber;
}
