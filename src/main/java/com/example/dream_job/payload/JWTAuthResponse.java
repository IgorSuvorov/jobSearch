package com.example.dream_job.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Igor Suvorov
 */

@Getter
@Setter
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
