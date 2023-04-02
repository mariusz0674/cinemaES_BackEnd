package com.example.cinemaES.config;

import com.example.cinemaES.entity.User;
import com.example.cinemaES.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        User userA=user.get();
        List<String> roles = new ArrayList<>();
        roles.add(userA.getRole().name());

        return org.springframework.security.core.userdetails.User.withUsername(userA.getUsername())
                .password(userA.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
    }
}
