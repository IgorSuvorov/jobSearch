package com.example.dream_job.service;

import com.example.dream_job.model.City;
import com.example.dream_job.payload.JobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Igor Suvorov
 */
public interface JobService {
    JobDTO save(JobDTO jobDTO);

    JobDTO update(long id, JobDTO jobDTO);

    void delete(long id);

    JobDTO findJobById(long id);

    Page<JobDTO> getAllJobs(int pageNo, int pageSize, String sortBy, String sortDir);

    Page<JobDTO> findJobsByCity(City city, Pageable pageable);

    Page<JobDTO> findJobsBySkills(String skill, Pageable pageable);

    Page<JobDTO> findJobsByTitle(String title, Pageable pageable);

    Page<JobDTO> findJobsByTitleAndCity(String title, City city, Pageable pageable);
}
