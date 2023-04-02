package com.example.cinemaES.service;

import com.example.cinemaES.dto.UserDto;
import com.example.cinemaES.entity.User;
import com.example.cinemaES.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManagmentService {
    private final UserRepository userRepository;

    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }




    public Boolean deleteUserById(Integer idToDell) {
        Optional userToDell = userRepository.findById(idToDell);
        if(userToDell.isPresent()){
            userRepository.delete((User) userToDell.get());
            return true;
        }else{
            return false;
        }

    }

    public Boolean updateUserRole(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow( () -> new UsernameNotFoundException( userDto.getUsername()));
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return true;
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    private User mapDto(UserDto user){
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}
