package com.example.dream_job.repository;

import com.example.dream_job.model.City;
import com.example.dream_job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Igor Suvorov
 */
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job>  findBySkillsContaining(String skill);

    List<Job> findAllByCity(City city);

    List<Job>  findByTitle(String title);

    List<Job> findAllBySkillsContaining(String skill);

    List<Job> findAllByTitleContaining(String title);

    List<Job> findJobsByTitleAndCompanyName(String title, String companyName);
}
