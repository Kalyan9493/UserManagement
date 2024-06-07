package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.dto.LoginDTO;
import com.usermanagement.usermanagement.dto.UserDTO;
import com.usermanagement.usermanagement.model.User;
import com.usermanagement.usermanagement.service.UserService;
import com.usermanagement.usermanagement.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public ResponseEntity<ApiResponse> save(@RequestBody User user) {

        return  new ResponseEntity<>(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        null,
                        userService.save(user)),
                HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getUserDetails(@PathVariable Long id) {

        return  new ResponseEntity<>(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        null,
                        userService.getUser(id)),
                HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO loginDTO) {

        return new ResponseEntity<>(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        null,
                        userService.login(loginDTO)),
                HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getAllUsers() {

        return new ResponseEntity<>(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        null,
                        userService.getAllUsers()),
                HttpStatus.OK);
    }

    @PutMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserDTO userDTO) {

        return new ResponseEntity<>(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        null,
                        userService.updateUser(userDTO)),
                HttpStatus.OK);
    }

}
