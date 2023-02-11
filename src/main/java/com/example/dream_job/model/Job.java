package com.example.dream_job.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Job implements Serializable {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String title;
    @NonNull
    private String companyName;
    @NonNull
    private City city;
    @NonNull
    private String description;
    @NonNull
    private List<String> skills;
    @NonNull
    private Date updated;
}
