package com.example.dream_job.service;

import com.example.dream_job.model.City;
import com.example.dream_job.model.Job;
import com.example.dream_job.payload.JobDTO;
import com.example.dream_job.payload.JobResponse;

import java.util.List;

/**
 * @author Igor Suvorov
 */
public interface JobService {
    JobDTO save(JobDTO jobDTO);
    JobDTO update(long id, JobDTO jobDTO);
    List<JobDTO> findJobsByCity(City city);
    List<JobDTO> findJobsBySkills(String skill);
    List<JobDTO> findJobByTitle(String title);
    List<JobDTO> findJobsByTitleAndCompanyName(String title, String companyName);
    JobDTO findById(long id);
    void delete(long id);
    JobResponse getAllJobs(int pageNo, int pageSize, String sortBy, String sortDir);
}
