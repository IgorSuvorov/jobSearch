package com.example.dream_job.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Igor Suvorov
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApplicantNotFoundException extends RuntimeException {
    private long id;

    public ApplicantNotFoundException(long id) {
        super(String.format("no applicants found with id: " + id));
        this.id = id;
    }
}
