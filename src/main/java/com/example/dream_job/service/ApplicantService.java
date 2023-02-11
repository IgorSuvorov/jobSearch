package com.example.dream_job.service;

import com.example.dream_job.model.City;
import com.example.dream_job.payload.ApplicantDTO;
import com.example.dream_job.payload.JobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Igor Suvorov
 */
public interface ApplicantService {
    ApplicantDTO save(ApplicantDTO applicantDTO);

    ApplicantDTO update(long id, ApplicantDTO applicantDTO);

    void delete(long id);

    ApplicantDTO findById(long id);

    Page<ApplicantDTO> getAllApplicants(int pageNo, int pageSize, String sortBy, String sortDir);

    Page<ApplicantDTO> findApplicantsBySkills(String skill, Pageable pageable);

    Page<ApplicantDTO> findApplicantsByTitle(String title, Pageable pageable);

    Page<ApplicantDTO> findApplicantsByCity(City city, Pageable pageable);

    Page<ApplicantDTO> findApplicantsByTitleAndCity(String title, City city, Pageable pageable);
}
