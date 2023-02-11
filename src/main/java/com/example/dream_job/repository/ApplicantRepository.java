package com.example.dream_job.repository;

import com.example.dream_job.model.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Suvorov
 */
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Page<Applicant> findApplicantsBySkills(String skill, Pageable pageable);

    Page<Applicant> findApplicantsByCity(String city, Pageable pageable);

    Page<Applicant> findApplicantsByTitleAndCity(String title, String city, Pageable pageable);

    Page<Applicant> findApplicantsByTitle(String title, Pageable pageable);
}
