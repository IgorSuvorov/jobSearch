package com.example.dream_job.exceptions;

/**
 * @author Igor Suvorov
 */
public class JobNotFoundException extends RuntimeException {
    private long id;

    public JobNotFoundException(long id) {
        super(String.format("no jobs found with id: " + id));
        this.id = id;
    }
}
