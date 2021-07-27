package com.abhinav.dhamaniya.StockExchange.service;

import com.abhinav.dhamaniya.StockExchange.dto.UserDto;
import com.abhinav.dhamaniya.StockExchange.dto.request.UserLoginRequest;
import com.abhinav.dhamaniya.StockExchange.entities.User;
import com.abhinav.dhamaniya.StockExchange.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.exception.UserNotConfirmed;
import com.abhinav.dhamaniya.StockExchange.mapper.UserMapper;
import com.abhinav.dhamaniya.StockExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    UserMapper userMapper;

    public int signUpUser(@RequestBody UserDto userDto) throws MessagingException {
        int generatedUserId = userRepository.save(userMapper.convertToEntity(userDto)).getId();
        String emailBody = "<html><h2>Click on the below link to confirm your account</h2><a href='https://stock-exchange-app-abhinav.herokuapp.com/users/confirmUser/"+generatedUserId+"'>Confirm User</a></html>";
        emailService.sendMail(userDto.getEmail(), "Verify your account for Stock Exchange Application", emailBody);
        return generatedUserId;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int confirmUserViaMail(int userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            user.setConfirmed(true);
            userRepository.save(user);
            return user.getId();
        }
        else throw new EntityNotFoundException("User with user ID: "+userId+" Not Found.");
    }

    public int updateUser(int id, UserDto userDto) throws EntityNotFoundException, UserNotConfirmed {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new EntityNotFoundException("User with userId: "+id+" not found.");
        if(user.getConfirmed() == false) throw new UserNotConfirmed("User with userId: "+id+" is not confirmed.");
        User updatedUserEntity = userMapper.convertToEntity(userDto);
        updatedUserEntity.setConfirmed(true);
        updatedUserEntity.setId(id);
        return userRepository.save(updatedUserEntity).getId();
    }

    public boolean checkUserExists(@PathVariable int id) {
        if(userRepository.findById(id).isPresent()) return true;
        return false;
    }
    public boolean checkConfirmedUserExists(@PathVariable int id) {
        if(userRepository.findByIdAndConfirmed(id).isPresent()) return true;
        return false;
    }
    public UserDto validateLoginAndGetConfirmedUser(UserLoginRequest userLoginRequest) throws EntityNotFoundException {
        Optional<User> user = userRepository.validateUsernamePassword(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        if(!user.isPresent()) throw new EntityNotFoundException("User not found");
        UserDto userDto = userMapper.convertToDto(user.orElse(null));
        return userDto;
    }
}