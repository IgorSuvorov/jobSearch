package com.example.dream_job.service;

import com.example.dream_job.exceptions.JobNotFoundException;
import com.example.dream_job.model.City;
import com.example.dream_job.model.Job;
import com.example.dream_job.payload.JobDTO;
import com.example.dream_job.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dream_job.payload.JobResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
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

    @Override
    public JobResponse getAllJobs(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Job> jobs = jobRepository.findAll(pageable);
        List<Job> listOfJobs = jobs.getContent();

        List<JobDTO> content = listOfJobs.stream().
                map(job -> mapEntityToDTO(job)).
                collect(Collectors.toList());

        JobResponse jobResponse = new JobResponse();
        jobResponse.setContent(content);
        jobResponse.setPageNo(jobs.getNumber());
        jobResponse.setPageSize(jobs.getSize());
        jobResponse.setTotalElements(jobs.getTotalElements());
        jobResponse.setTotalPages(jobs.getTotalPages());
        jobResponse.setLast(jobs.isLast());

        return jobResponse;
    }

    @Override
    public List<JobDTO> findJobsByCity(City city) {
        List<Job> jobs = jobRepository.findAllByCity(city);
        List<JobDTO> jobDTOs = new ArrayList<>();
        for (Job job : jobs) {
            jobDTOs.add(mapEntityToDTO(job));
        }
        return jobDTOs;
    }

    @Override
    public List<JobDTO> findJobsBySkills(String skill) {
        List<Job> jobs = jobRepository.findAllBySkillsContaining(skill);
        List<JobDTO> jobDTOs = new ArrayList<>();
        for (Job job : jobs) {
            jobDTOs.add(mapEntityToDTO(job));
        }
        return jobDTOs;
    }

    @Override
    public List<JobDTO> findJobByTitle(String title) {
        List<Job> jobs = jobRepository.findAllByTitleContaining(title);
        List<JobDTO> jobDTOs = new ArrayList<>();
        for (Job job : jobs) {
            jobDTOs.add(mapEntityToDTO(job));
        }
        return jobDTOs;
    }

    @Override
    public List<JobDTO> findJobsByTitleAndCompanyName(String title, String companyName) {
        List<Job> jobs = jobRepository.findJobsByTitleAndCompanyName(title, companyName);
        List<JobDTO> jobDTOs = new ArrayList<>();
        for (Job job : jobs) {
            jobDTOs.add(mapEntityToDTO(job));
        }
        return jobDTOs;
    }

    @Override
    public JobDTO findById(long id) {
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
