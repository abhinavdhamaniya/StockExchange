package com.abhinav.dhamaniya.StockExchange.dto.response;

import com.abhinav.dhamaniya.StockExchange.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
    String token;
    UserDto userDto;
}
