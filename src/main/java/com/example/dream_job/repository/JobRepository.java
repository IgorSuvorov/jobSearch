package com.example.dream_job.repository;

import com.example.dream_job.model.City;
import com.example.dream_job.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Igor Suvorov
 */
public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findJobsByCity(City city, Pageable pageable);

    Page<Job> findJobsBySkills(String skill, Pageable pageable);

    Page<Job> findJobsByTitle(String title, Pageable pageable);

    Page<Job> findJobsByTitleAndCity(String title, City city, Pageable pageable);
}
