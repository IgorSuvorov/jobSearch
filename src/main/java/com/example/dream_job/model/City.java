package com.example.dream_job.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    private long id;
    private String cityName;
    private String state;
}
