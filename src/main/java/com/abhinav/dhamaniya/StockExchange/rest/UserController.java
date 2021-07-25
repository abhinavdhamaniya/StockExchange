package com.abhinav.dhamaniya.StockExchange.rest;

import com.abhinav.dhamaniya.StockExchange.dto.UserDto;
import com.abhinav.dhamaniya.StockExchange.dto.request.UserLoginRequest;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityCreatedResponse;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityNotFoundResponse;
import com.abhinav.dhamaniya.StockExchange.dto.response.ErrorOccurred;
import com.abhinav.dhamaniya.StockExchange.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.exception.UserNotConfirmed;
import com.abhinav.dhamaniya.StockExchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity signUpUser(@RequestBody UserDto userDto) {

        int generatedUserId;
        try {
            generatedUserId = userService.signUpUser(userDto);
        }
        catch (MessagingException messagingException)
        {
            return new ResponseEntity(new ErrorOccurred("Error occurred while sending confirmation mail."), HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping(value = "/confirmUser/{id}")
    public ResponseEntity confirmUserViaMail(@PathVariable int id) {

        int generatedUserId;
        try {
            generatedUserId = userService.confirmUserViaMail(id);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new ErrorOccurred("User Not Found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new EntityCreatedResponse(generatedUserId, "User Confirmed."), HttpStatus.OK);
    }

    @PutMapping(value = "/updateUser/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody UserDto userDto) {

        int returnedUserId;
        try {
            returnedUserId = userService.updateUser(id, userDto);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new EntityNotFoundResponse("User with userId: "+id+" not found."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (UserNotConfirmed userNotConfirmed)
        {
            return new ResponseEntity(new EntityNotFoundResponse("User with userId: "+id+" not confirmed."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception exception)
        {
            return new ResponseEntity(new ErrorOccurred("Error Occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new EntityCreatedResponse(returnedUserId, "User Updated."), HttpStatus.OK);
    }

    @GetMapping(value = "checkExists/{id}")
    public ResponseEntity checkUserExists(@PathVariable int id) {
        boolean exists = userService.checkUserExists(id);
        if(exists) return new ResponseEntity(true, HttpStatus.OK);
        else return new ResponseEntity(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "checkExistsAndConfirmed/{id}")
    public ResponseEntity checkConfirmedUserExists(@PathVariable int id) {
        boolean exists = userService.checkConfirmedUserExists(id);
        if(exists) return new ResponseEntity(true, HttpStatus.OK);
        else return new ResponseEntity(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "validateLoginAndGetConfirmedUser")
    public ResponseEntity validateLoginAndGetConfirmedUser(@RequestBody UserLoginRequest userLoginRequest) {

        UserDto userDto = null;
        try {
            userDto = userService.validateLoginAndGetConfirmedUser(userLoginRequest);
        }
        catch (EntityNotFoundException entityNotFoundException) {
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(userDto, HttpStatus.OK);
    }
}
