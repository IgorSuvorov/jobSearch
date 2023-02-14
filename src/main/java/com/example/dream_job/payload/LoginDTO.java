package com.example.dream_job.payload;

import lombok.Data;

/**
 * @author Igor Suvorov
 */
@Data
public class LoginDTO {
    private String userNameOrEmail;
    private String password;
}
