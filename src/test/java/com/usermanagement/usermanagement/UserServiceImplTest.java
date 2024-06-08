package com.usermanagement.usermanagement;

import com.usermanagement.usermanagement.dto.LoginDTO;
import com.usermanagement.usermanagement.dto.UserDTO;
import com.usermanagement.usermanagement.exceptionhandlers.UserException;
import com.usermanagement.usermanagement.jwt.JWTService;
import com.usermanagement.usermanagement.model.Role;
import com.usermanagement.usermanagement.model.User;
import com.usermanagement.usermanagement.model.UserRole;
import com.usermanagement.usermanagement.repository.UserRepository;
import com.usermanagement.usermanagement.repository.UserRoleRepository;
import com.usermanagement.usermanagement.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private JWTService jwtService;

    @Mock
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void testSaveUser_Valid() {
        User user = new User();
        user.setEmailId("test@example.com");
        user.setMobileNumber(1234567890L);
        user.setCountryCode("+1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");

        when(userRepository.findUserByEmailOrMobile(anyString(), anyLong())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRoleRepository.save(any(UserRole.class))).thenReturn(null);

        UserDTO userDTO = new UserDTO();
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO result = userService.save(user);

        assertNotNull(result);
        verify(userRepository, times(1)).findUserByEmailOrMobile(anyString(), anyLong());
        verify(userRepository, times(1)).save(any(User.class));
        verify(userRoleRepository, times(1)).save(any(UserRole.class));
    }

    @Test
    public void testSaveUser_InvalidEmail() {
        User user = new User();
        user.setEmailId("invalid-email");
        user.setMobileNumber(1234567890L);
        user.setCountryCode("+1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");

        assertThrows(UserException.class, () -> userService.save(user));
    }

    @Test
    public void testGetUser_Valid() {
        User user = new User();
        user.setUserId(1L);

        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(new UserDTO());
        when(userRepository.findById(1L)).thenReturn(Optional.of((user)));


        UserDTO result = userService.getUser(1L);

        assertNotNull(result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUser_Invalid() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> userService.getUser(1L));
    }

    @Test
    public void testLogin_ValidEmail() {
        User user = new User();
        user.setEmailId("test@example.com");
        user.setPassword(passwordEncoder.encode("password"));

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("test@example.com");
        loginDTO.setPassword("password");

        when(userRepository.findUserByEmailOrMobile(loginDTO.getUsername(), null)).thenReturn(user);
        when(jwtService.generateToken(any(User.class))).thenReturn("token");
        UserDTO userDTO = new UserDTO();
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO result = userService.login(loginDTO);

        assertNotNull(result);
        verify(userRepository, times(1)).findUserByEmailOrMobile(loginDTO.getUsername(), null);
    }

    @Test
    public void testLogin_InvalidUser() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("test@example.com");
        loginDTO.setPassword("password");

        when(userRepository.findUserByEmailOrMobile(loginDTO.getUsername(), null)).thenReturn(null);

        assertThrows(UserException.class, () -> userService.login(loginDTO));
    }

    @Test
    public void testGetAllUsers() {
        User user = new User();
        List<User> users = Collections.singletonList(user);

        when(userRepository.findAll()).thenReturn(users);
        UserDTO userDTO = new UserDTO();
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        List<UserDTO> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateUser_Valid() {
        User user = new User();
        user.setUserId(1L);
        user.setEmailId("test@example.com");

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setEmailId("test@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO result = userService.updateUser(userDTO);

        assertNotNull(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_InvalidEmail() {
        User user = new User();
        user.setUserId(1L);
        user.setEmailId("test@example.com");

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setEmailId("invalid-email");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertThrows(UserException.class, () -> userService.updateUser(userDTO));
    }

    @Test
    public void testLoadUserByUsername_Valid() {
        User user = new User();
        user.setEmailId("test@example.com");
        user.setPassword("password");
        Role role = new Role();
        role.setName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        when(userRepository.findUserByEmailOrMobile("test@example.com", null)).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_Invalid() {
        when(userRepository.findUserByEmailOrMobile("test@example.com", null)).thenReturn(null);

        assertThrows(UserException.class, () -> userService.loadUserByUsername("test@example.com"));
    }

    @Test
    public void testGetCurrentUsername() {
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User("test@example.com", "password", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null));

        String username = userService.getCurrentUsername();

        assertEquals("test@example.com", username);
    }
}
