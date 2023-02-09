package com.example.dream_job.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Job implements Serializable {
    @NonNull
    private long id;
    @NonNull
    private String name;
    private City city;
    private String description;
}
