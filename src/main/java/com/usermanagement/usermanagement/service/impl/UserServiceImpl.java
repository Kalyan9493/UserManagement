package com.usermanagement.usermanagement.service.impl;

import com.usermanagement.usermanagement.dto.LoginDTO;
import com.usermanagement.usermanagement.dto.UserDTO;
import com.usermanagement.usermanagement.handlers.UserException;
import com.usermanagement.usermanagement.model.User;
import com.usermanagement.usermanagement.repository.UserRepository;
import com.usermanagement.usermanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private ModelMapper modelMapper = new ModelMapper();
    private static final String COUNTRY_CODE_REGEX = "^\\+[1-9]\\d{0,2}$"; // Regular expression for country codes with international dialing code
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String MOBILE_NUMBER_REGEX = "^[0-9]{10}$";

    public static boolean isValidMobileNumber(String mobileNumber) {
        Pattern pattern = Pattern.compile(MOBILE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidCountryCode(String countryCode) {
        Pattern pattern = Pattern.compile(COUNTRY_CODE_REGEX);
        Matcher matcher = pattern.matcher(countryCode);
        return matcher.matches();
    }
    public static boolean isNumeric(String str) {
        String regex = "^\\d+$";
        return str.matches(regex);
    }

    public static boolean isEmail(String str) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    public static boolean validateUser(Optional<User> user) {
        if(!user.isPresent()){
            return false;
        }
        user.ifPresent(userObj -> {
            if(userObj.getCountryCode() == null || !isValidCountryCode(userObj.getCountryCode())){
                throw new UserException(400, "Country code is incorrect");
            }
            if(userObj.getEmailId() == null || !isValidEmail(userObj.getEmailId())){
                throw new UserException(400, "Email id is incorrect");
            }
            if(userObj.getFirstName() == null || userObj.getLastName() == null){
                throw new UserException(400, "First name and last name are required");
            }
            if(userObj.getMobileNumber() == null || !isValidMobileNumber(String.valueOf(userObj.getMobileNumber()))){
                throw new UserException(400, "Mobile number is incorrect");
            }
            if(userObj.getPassword() == null){
                throw new UserException(400, "Password is required");
            }
        });

        return true;
    }
    @Override
    public UserDTO save(User user) {

        if(validateUser(Optional.ofNullable(user))){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User existingUser = userRepository.findUserByEmailOrMobile(user.getEmailId(), user.getMobileNumber());
            if(existingUser != null){
                throw new UserException(409,"User already exists with this email or mobile number");
            }
            user = userRepository.save(user);
        }
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO getUser(Long id) {
        if(id == null){
            throw new UserException(400,"UserId is required");
        }
        Optional<User> user = userRepository.findById(id);
        UserDTO userDTO = modelMapper.map(Optional.ofNullable(user),UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO login(LoginDTO loginDTO) {

        if(loginDTO == null || loginDTO.getUsername() == null || loginDTO.getPassword() == null) {
            throw new UserException(400, "Email/Mobile and Password are required");
        }
        String emailId = isEmail(loginDTO.getUsername()) ? loginDTO.getUsername() : null;
        Long mobileNumber = isNumeric(loginDTO.getUsername()) ? Long.parseLong(loginDTO.getUsername()) : null;
        String password = passwordEncoder.encode(loginDTO.getPassword());
        User user = userRepository.login(emailId, mobileNumber, password);
        if(user == null){
            throw new UserException(404, "User not found");
        }
        UserDTO userDTO = modelMapper.map(Optional.ofNullable(user),UserDTO.class);
        return userDTO;
    }
}
