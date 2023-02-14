package com.example.dream_job.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Igor Suvorov
 */
@Getter
public class JobSearchAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public JobSearchAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public JobSearchAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }
}
