package com.example.dream_job.repository;

import com.example.dream_job.model.Applicant;
import com.example.dream_job.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Igor Suvorov
 */
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Optional<Applicant> findByFirstNameAndLastName(String first, String last);

    Optional<Applicant> findBySkillsContaining(String skill);
    Optional<Applicant> findByCity(City city);
}
