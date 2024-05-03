package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.dto.LoginDTO;
import com.usermanagement.usermanagement.dto.ResponseDTO;
import com.usermanagement.usermanagement.dto.UserDTO;
import com.usermanagement.usermanagement.handlers.UserException;
import com.usermanagement.usermanagement.model.User;
import com.usermanagement.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public ResponseDTO save(@RequestBody  User user){
        try{
            UserDTO userDTO = userService.save(user);
            return new ResponseDTO("success", HttpStatus.OK.value(), userDTO);
        }catch(UserException ex){
            return new ResponseDTO("error", ex.getCode(), ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @GetMapping("/user")
    public String example(){
        return "Hello World";
    }

    @GetMapping("/user/{id}")
    public ResponseDTO getUserDetails(@PathVariable Long id){
        try{
            UserDTO userDTO = userService.getUser(id);
            return new ResponseDTO("success", HttpStatus.OK.value(), userDTO);
        }catch(UserException ex){
            return new ResponseDTO("error", ex.getCode(), ex.getMessage());
        }catch (Exception ex){
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @PostMapping("/user/login")
    public ResponseDTO login(@RequestBody LoginDTO loginDTO){
        try{
            UserDTO userDTO = userService.login(loginDTO);
            return new ResponseDTO("success", HttpStatus.OK.value(), userDTO);
        }catch(UserException ex){
            return new ResponseDTO("error", ex.getCode(), ex.getMessage());
        }catch (Exception ex){
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

}
