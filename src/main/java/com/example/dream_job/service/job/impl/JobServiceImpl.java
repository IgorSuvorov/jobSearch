package com.example.dream_job.service.job.impl;

import com.example.dream_job.exceptions.JobNotFoundException;
import com.example.dream_job.model.Job;
import com.example.dream_job.payload.JobDTO;
import com.example.dream_job.repository.JobRepository;
import com.example.dream_job.service.job.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Suvorov
 */
@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private ModelMapper modelMapper;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobDTO save(JobDTO jobDTO) {
        return mapEntityToDTO(jobRepository.save(mapDTOToEntity(jobDTO)));
    }

    @Override
    public JobDTO update(long id, JobDTO jobDTO) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));

        job.setTitle(jobDTO.getTitle());
        job.setCompanyName(jobDTO.getCompanyName());
        job.setCity(jobDTO.getCity());
        job.setDescription(jobDTO.getDescription());
        job.setSkills(jobDTO.getSkills());
        Job updatedJob = jobRepository.save(job);

        return mapEntityToDTO(updatedJob);
    }

    public Page<JobDTO> getAllJobs(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Job> jobs = jobRepository.findAll(pageable);

        return jobs.map(this::mapEntityToDTO);
    }

    @Override
    public Page<JobDTO> findJobsByTitleAndCity(String title, String city, Pageable pageable) {
        Page<Job> jobs = jobRepository.findJobsByTitleAndCity(title, city, pageable);
        List<JobDTO> jobDTOs = jobs.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
        return new PageImpl<>(jobDTOs, pageable, jobs.getTotalElements());
    }

    @Override
    public Page<JobDTO> findJobsByCity(String city, Pageable pageable) {
        Page<Job> jobs = jobRepository.findJobsByCity(city, pageable);
        List<JobDTO> jobDTOs = jobs.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
        return new PageImpl<>(jobDTOs, pageable, jobs.getTotalElements());
    }

    @Override
    public Page<JobDTO> findJobsBySkills(String skill, Pageable pageable) {
        Page<Job> jobs = jobRepository.findJobsBySkills(skill, pageable);
        return jobs.map(this::mapEntityToDTO);
    }

    @Override
    public Page<JobDTO> findJobsByTitle(String title, Pageable pageable) {
        Page<Job> jobs = jobRepository.findJobsByTitle(title, pageable);
        return jobs.map(this::mapEntityToDTO);
    }

    @Override
    public JobDTO findJobById(long id) {
        Job job = jobRepository.findById(id).
                orElseThrow(() -> new JobNotFoundException(id));
        return mapEntityToDTO(job);
    }

    @Override
    public void delete(long id) {
        jobRepository.deleteById(id);
    }

    private JobDTO mapEntityToDTO(Job job) {
        JobDTO jobDTO = modelMapper.map(job, JobDTO.class);
        return jobDTO;
    }

    private Job mapDTOToEntity(JobDTO jobDTO) {
        Job job = modelMapper.map(jobDTO, Job.class);
        return job;
    }
}
