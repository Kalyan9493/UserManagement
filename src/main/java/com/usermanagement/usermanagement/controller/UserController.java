package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.dto.LoginDTO;
import com.usermanagement.usermanagement.dto.ResponseDTO;
import com.usermanagement.usermanagement.dto.UserDTO;
import com.usermanagement.usermanagement.handlers.UserException;
import com.usermanagement.usermanagement.model.User;
import com.usermanagement.usermanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public ResponseDTO save(@RequestBody  User user){
        logger.trace("save() method start");
        try{
            UserDTO userDTO = userService.save(user);
            logger.info("User saved successfully");
            return new ResponseDTO("success", HttpStatus.OK.value(), userDTO);
        }catch(UserException ex){
            logger.error("Exception while saving user " + ex.getMessage());
            return new ResponseDTO("error", ex.getCode(), ex.getMessage());
        }catch (Exception ex){
            logger.error("Exception while saving user " + ex.getMessage());
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }finally {
            logger.trace("save() method end");
        }

    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDTO getUserDetails(@PathVariable Long id){
        logger.trace("getUserDetails() method start");
        try{
            UserDTO userDTO = userService.getUser(id);
            logger.info("User details fetched successfully");
            return new ResponseDTO("success", HttpStatus.OK.value(), userDTO);
        }catch(UserException ex){
            logger.error("Exception while fetching user" + ex.getMessage());
            return new ResponseDTO("error", ex.getCode(), ex.getMessage());
        }catch (Exception ex){
            logger.error("Exception while fetching user" + ex.getMessage());
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }finally {
            logger.trace("getUserDetails() method end");
        }
    }

    @PostMapping("/user/login")
    public ResponseDTO login(@RequestBody LoginDTO loginDTO){
        logger.trace("login() method start");
        try{
            UserDTO userDTO = userService.login(loginDTO);
            logger.info("User logged in successfully");
            return new ResponseDTO("success", HttpStatus.OK.value(), userDTO);
        }catch(UserException ex){
            logger.error("Exception while login " + ex.getMessage());
            return new ResponseDTO("error", ex.getCode(), ex.getMessage());
        }catch (Exception ex){
            logger.error("Exception while login " + ex.getMessage());
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }finally {
            logger.trace("login() method end");
        }
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDTO getAllUsers(){
        logger.trace("getAllUsers() method start");
        try {
            List<UserDTO> userDTOS = userService.getAllUsers();
            logger.info("fetched all users successfully");
            return new ResponseDTO("success", HttpStatus.OK.value(), userDTOS);
        }catch (Exception ex){
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }finally {
            logger.trace("getAllUsers() method end");
        }
    }
    @PutMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDTO updateUser(@RequestBody UserDTO userDTO) {
        logger.trace("updateUser() method start");
        try {
            UserDTO updatedUser = userService.updateUser(userDTO);
            logger.info("update user successfully");
            return new ResponseDTO("success", HttpStatus.OK.value(), updatedUser);
        }catch(UserException ex){
            logger.error("Exception while updating user " + ex.getMessage());
            return new ResponseDTO("error", ex.getCode(), ex.getMessage());
        }catch (Exception ex){
            logger.error("Exception while updating user " + ex.getMessage());
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }finally {
            logger.trace("updateUser() method end");
        }
    }

}
