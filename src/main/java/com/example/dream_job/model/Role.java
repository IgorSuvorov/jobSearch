package com.example.dream_job.model;

import lombok.Data;

import jakarta.persistence.*;

/**
 * @author Igor Suvorov
 */

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (length = 50)
    private String name;

}