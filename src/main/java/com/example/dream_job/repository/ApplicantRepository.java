package com.example.dream_job.repository;

import com.example.dream_job.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Suvorov
 */
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}
