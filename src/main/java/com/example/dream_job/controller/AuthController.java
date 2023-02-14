package com.example.dream_job.controller;

import com.example.dream_job.model.Role;
import com.example.dream_job.model.User;
import com.example.dream_job.payload.LoginDTO;
import com.example.dream_job.payload.SignupDTO;
import com.example.dream_job.repository.RoleRepository;
import com.example.dream_job.repository.UserRepository;
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

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO) {
        if (userRepository.existsByUserName(signupDTO.getUserName())) {
            return new ResponseEntity<>("User with this name already exists!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            return new ResponseEntity<>("User with this email already exists!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();

        user.setName(signupDTO.getName());
        user.setUserName(signupDTO.getUserName());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();

        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User has ben successfully registered!", HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to Signin or Login user to job search app")
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserNameOrEmail(),
                        loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User signed-in successfully!", HttpStatus.OK);
    }
}