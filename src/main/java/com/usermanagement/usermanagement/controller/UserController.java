package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.dto.ResponseDTO;
import com.usermanagement.usermanagement.dto.UserDTO;
import com.usermanagement.usermanagement.handlers.UserException;
import com.usermanagement.usermanagement.model.User;
import com.usermanagement.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseDTO save(User user){
        try{
            UserDTO userDTO = userService.save(user);
            return new ResponseDTO("success", HttpStatus.OK.value(), userDTO);
        }catch(UserException ex){
            return new ResponseDTO("error", ex.getCode(), ex.getMessage());
        }catch (Exception ex){
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @GetMapping("/user")
    public String example(){
        return "Hello World";
    }

}
