package com.hospital.hospital.controller;

import com.hospital.hospital.dto.LoginDTO;
import com.hospital.hospital.dto.RegisterDto;
import com.hospital.hospital.entity.User;
import com.hospital.hospital.repository.UserRepository;
import com.hospital.hospital.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = authService.registerUser(user);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> user = authService.authenticate(email, password);
        return user.isPresent()
                ? ResponseEntity.ok("Login successful for " + user.get().getName())
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}
