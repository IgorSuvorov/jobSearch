package com.example.dream_job.service;

import com.example.dream_job.model.Applicant;
import com.example.dream_job.model.City;
import com.example.dream_job.payload.ApplicantDTO;
import com.example.dream_job.payload.ApplicantResponse;

import java.util.Optional;

/**
 * @author Igor Suvorov
 */
public interface ApplicantService {
    public ApplicantDTO save(ApplicantDTO applicantDTO);

    public ApplicantDTO update(long id, ApplicantDTO applicantDTO);

    public ApplicantDTO findApplicantsByFirstAndLastName(String first, String last);

    public ApplicantDTO findApplicantsBySkills(String skill);

    public ApplicantDTO findApplicantsByCity(City city);

    public ApplicantDTO findById(long id);

    void delete(long id);

    public ApplicantResponse getAllApplicants(int pageNo, int pageSize, String sortBy, String sortDir);
}
