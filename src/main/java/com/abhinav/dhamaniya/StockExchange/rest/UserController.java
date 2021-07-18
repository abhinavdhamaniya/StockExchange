package com.abhinav.dhamaniya.StockExchange.rest;

import com.abhinav.dhamaniya.StockExchange.dto.UserDto;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityCreatedResponse;
import com.abhinav.dhamaniya.StockExchange.dto.response.ErrorOccurred;
import com.abhinav.dhamaniya.StockExchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity signUpUser(@RequestBody UserDto userDto) {

        int generatedUserId;
        try {
            generatedUserId = userService.signUpUser(userDto);
        }
        catch (Exception exception)
        {
            return new ResponseEntity(new ErrorOccurred("Error Occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new EntityCreatedResponse(generatedUserId, "User Created."), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }
}
