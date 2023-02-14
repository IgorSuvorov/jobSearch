package com.example.dream_job.model;

import lombok.*;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "job")
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
    private String city;
    @NonNull
    private String description;
    @ElementCollection
    private List<String> skills;
    @NonNull
    private Date updated;

}
