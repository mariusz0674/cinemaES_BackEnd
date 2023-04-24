package com.example.cinemaES.service;

import com.example.cinemaES.dto.Mapper;
import com.example.cinemaES.dto.UserDto;
import com.example.cinemaES.entity.User;
import com.example.cinemaES.enums.Role;
import com.example.cinemaES.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class UserManagmentServiceTest {

    @InjectMocks
    UserManagmentService userManagmentService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
       // MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void testGetAllUsers() {
        // given
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");
        user1.setFirstname("first1");
        user1.setLastname("last1");
        user1.setEmail("email1");
        user1.setPassword("1234");
        user1.setRole(Role.ADMIN);
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");
        user2.setFirstname("first2");
        user2.setLastname("last2");
        user2.setEmail("email2");
        user2.setPassword("1234");
        user2.setRole(Role.DEFAULT);
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        // when
        List<UserDto> result = userManagmentService.getAllUsers();

        // then
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("user1", result.get(0).getUsername());
        Assertions.assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    public void testDeleteUserById() {
        // given
        User user = new User();
        user.setId(1);
        user.setUsername("user1");
        user.setFirstname("first1");
        user.setLastname("last1");
        user.setEmail("email1");
        user.setRole(Role.DEFAULT);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // when
        boolean result = userManagmentService.deleteUserById(1);

        // then
        verify(userRepository, times(1)).delete(user);
        Assertions.assertTrue(result);
    }

    @Test
    public void testDeleteUserByIdNotFound() {
        // given
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // when
        boolean result = userManagmentService.deleteUserById(1);

        // then
        verify(userRepository, never()).delete(any(User.class));
        Assertions.assertFalse(result);
    }

    @Test
    public void testUpdateUserRole() {
        // given
        UserDto userDto = new UserDto();
        userDto.setUsername("user1");
        userDto.setRole(Role.CASHIER);
        User user = new User();
        user.setId(1);
        user.setUsername("user1");
        user.setFirstname("first1");
        user.setLastname("last1");
        user.setEmail("email1");
        user.setRole(Role.ATTENDANT);
        when(userRepository.findByUsername("user1")).thenReturn(Optional.of(user));

        // when
        boolean result = userManagmentService.updateUserRole(userDto);

        // then
        verify(userRepository, times(1)).save(user);
        Assertions.assertTrue(result);
        Assertions.assertEquals(Role.CASHIER, user.getRole());
    }

}