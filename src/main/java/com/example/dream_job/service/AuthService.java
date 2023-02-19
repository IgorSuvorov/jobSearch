package com.example.dream_job.service;


import com.example.dream_job.payload.LoginDTO;
import com.example.dream_job.payload.SignupDTO;

/**
 * @author Igor Suvorov
 */
public interface AuthService {
    String login(LoginDTO loginDTO);

    String signup(SignupDTO signupDTO);
}