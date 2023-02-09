package com.example.dream_job.service;

import com.example.dream_job.model.Applicant;
import com.example.dream_job.model.City;
import com.example.dream_job.payload.ApplicantDTO;

import java.util.Optional;

/**
 * @author Igor Suvorov
 */
public interface ApplicantService {
    public ApplicantDTO save(ApplicantDTO applicantDTO);

    public ApplicantDTO update(long id, ApplicantDTO applicantDTO);

    public Optional<Applicant> findApplicantsByFirstAndLastName(String first, String last);

    public Optional<Applicant> findApplicantsBySkills(String skill);

    public Optional<Applicant> findApplicantsByCity(City city);

    Object findAll();
}
