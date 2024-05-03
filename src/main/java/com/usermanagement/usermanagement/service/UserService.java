package com.usermanagement.usermanagement.service;

import com.usermanagement.usermanagement.dto.LoginDTO;
import com.usermanagement.usermanagement.dto.UserDTO;
import com.usermanagement.usermanagement.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDTO save(User user);
    public UserDTO getUser(Long id);
    public UserDTO login(LoginDTO loginDTO);
}
