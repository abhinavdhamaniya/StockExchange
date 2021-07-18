package com.abhinav.dhamaniya.StockExchange.service;

import com.abhinav.dhamaniya.StockExchange.dto.UserDto;
import com.abhinav.dhamaniya.StockExchange.entities.User;
import com.abhinav.dhamaniya.StockExchange.mapper.UserMapper;
import com.abhinav.dhamaniya.StockExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public int signUpUser(@RequestBody UserDto userDto) {
        return userRepository.save(userMapper.convertToEntity(userDto)).getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}