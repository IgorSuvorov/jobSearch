package com.example.dream_job.payload;

import lombok.Data;

/**
 * @author Igor Suvorov
 */
@Data
public class SignupDTO {
    private String name;
    private String userName;
    private String email;
    private String password;
}
