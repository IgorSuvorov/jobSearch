package com.example.dream_job.model;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "applicant")
public class Applicant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String city;

    private String firstName;
    private String lastName;
    private String title;

    @ElementCollection
    private List<String> skills;

    private Date updated;
}
