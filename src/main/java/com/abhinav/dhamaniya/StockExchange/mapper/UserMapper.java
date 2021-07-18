package com.abhinav.dhamaniya.StockExchange.mapper;

import com.abhinav.dhamaniya.StockExchange.dto.UserDto;
import com.abhinav.dhamaniya.StockExchange.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User convertToEntity(UserDto userDto);
    UserDto convertToDto(User user);
}
