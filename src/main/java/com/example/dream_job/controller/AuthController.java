package com.example.dream_job.controller;

import com.example.dream_job.model.Role;
import com.example.dream_job.model.User;
import com.example.dream_job.payload.JWTAuthResponse;
import com.example.dream_job.payload.LoginDTO;
import com.example.dream_job.payload.SignupDTO;
import com.example.dream_job.repository.RoleRepository;
import com.example.dream_job.repository.UserRepository;
import com.example.dream_job.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author Igor Suvorov
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/signup"})
    public ResponseEntity<String> register(@RequestBody SignupDTO signupDTO){
        String response = authService.signup(signupDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}