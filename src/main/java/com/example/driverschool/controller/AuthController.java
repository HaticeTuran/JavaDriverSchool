package com.example.driverschool.controller;

import com.example.driverschool.dto.LoginResponse;
import com.example.driverschool.model.User;
import com.example.driverschool.security.jwt.JwtTokenProvider;
import com.example.driverschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user) {
        try {
            System.out.println("Registering user: " + user);
            User registeredUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }



    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Optional<User> optionalUser = userService.loadUserByUsername(loginRequest.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (userService.passwordMatches(loginRequest.getPassword(), user.getPassword())) {
                    String token = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(user, null));
                    return ResponseEntity.ok(Map.of("message", "Login successful", "token", token));
                } else {
                    return ResponseEntity.status(401).body("Invalid username or password");
                }
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }


    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
