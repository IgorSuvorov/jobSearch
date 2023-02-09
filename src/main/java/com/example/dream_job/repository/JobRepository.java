package com.example.dream_job.repository;

import com.example.dream_job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Suvorov
 */
public interface JobRepository extends JpaRepository<Job, Long> {
}
