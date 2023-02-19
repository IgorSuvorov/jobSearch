package com.example.dream_job.repository;

import com.example.dream_job.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Igor Suvorov
 */

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findJobsByCity(String city, Pageable pageable);

    Page<Job> findJobsBySkills(String skill, Pageable pageable);

    Page<Job> findJobsByTitle(String title, Pageable pageable);

    Page<Job> findJobsByTitleAndCity(String title, String city, Pageable pageable);
}
