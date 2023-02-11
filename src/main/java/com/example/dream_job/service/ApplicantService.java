package com.example.dream_job.service;

import com.example.dream_job.model.City;
import com.example.dream_job.payload.ApplicantDTO;

/**
 * @author Igor Suvorov
 */
public interface ApplicantService {
    ApplicantDTO save(ApplicantDTO applicantDTO);

    ApplicantDTO update(long id, ApplicantDTO applicantDTO);

    ApplicantDTO findApplicantsByFirstAndLastName(String first, String last);

    ApplicantDTO findApplicantsBySkills(String skill);

    ApplicantDTO findApplicantsByCity(City city);

    ApplicantDTO findById(long id);

    void delete(long id);

    ApplicantResponse getAllApplicants(int pageNo, int pageSize, String sortBy, String sortDir);
}
