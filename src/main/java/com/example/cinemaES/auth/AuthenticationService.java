package com.example.cinemaES.auth;

import com.example.cinemaES.entity.User;
import com.example.cinemaES.enums.TokenType;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.cinemaES.repository.TokenRepository;
import com.example.cinemaES.repository.UserRepository;
import com.example.cinemaES.security.JwtService;
import com.example.cinemaES.entity.RefreshToken;
import com.example.cinemaES.enums.Role;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws EntityExistsException {
        User user = User.builder()
                .username(request.getUsername())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.DEFAULT)
                .build();


        if(repository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("User already exists");
        }
        User savedUser = repository.save(user);


        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

//        saveUserToken(savedUser, refreshToken);
        return AuthenticationResponse.builder()
                .jwttoken(jwtToken)
                .refreshToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken temp =  new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        authenticationManager.authenticate(temp);
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .refreshToken(refreshToken)
                .jwttoken(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = RefreshToken.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(refreshToken -> {
            refreshToken.setExpired(true);
            refreshToken.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(request.getRefreshToken())
                .build();
        var user = repository.findByUsername(request.getUserName())
                .orElseThrow();
        if(jwtService.isRefreshTokenValid(request.getUserName(), refreshToken)){
            return AuthenticationResponse.builder()
                    .jwttoken(jwtService.generateToken(user))
                    .build();
        }
       return null;
    }
}