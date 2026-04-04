package com.st.auth.service;

import com.st.auth.dto.AuthResponse;
import com.st.auth.dto.RegisterRequest;
import com.st.auth.entity.User;
import com.st.auth.exception.DuplicateResourceException;
import com.st.auth.exception.InvalidCredentialsException;
import com.st.auth.repository.UserRepository;
import com.st.auth.util.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SkillsTuteUserDetailsService customUserDetailsService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService,
                       SkillsTuteUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User(
                request.getName(),
                request.getGender(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                "ROLE_USER"
        );

        user.setDate(LocalDate.now());
        userRepository.save(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, user.getEmail(), user.getRole(), "User registered successfully");
    }

    public AuthResponse login(String email, String password) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (Exception ex) {
            throw new InvalidCredentialsException("Invalid user or password");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        String token = jwtService.generateToken(userDetails);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new AuthResponse(token, user.getEmail(), user.getRole(), "Login successful");
    }
}