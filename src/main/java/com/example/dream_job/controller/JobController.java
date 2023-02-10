package com.example.dream_job.controller;

import com.example.dream_job.model.City;
import com.example.dream_job.payload.JobDTO;
import com.example.dream_job.payload.JobResponse;
import com.example.dream_job.service.ApplicantService;
import com.example.dream_job.service.JobService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.dream_job.model.Job;
import com.example.dream_job.utilities.AppConstants;

import java.util.List;

/**
 * @author Igor Suvorov
 */

@Controller
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @ApiOperation(value = "Add new job REST API")
    @PreAuthorize("hasRole('RECRUITER')")
    @PostMapping("/addJob")
    public ResponseEntity<JobDTO> addJob(@Valid @RequestBody JobDTO jobDTO) {
        return new ResponseEntity<>(jobService.save(jobDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Jobs REST API")
    @GetMapping("/jobs")
    public JobResponse getAllJobs(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return jobService.getAllJobs(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "Update job profile By Id REST API")
    @PreAuthorize("hasRole('RECRUITER')")
    @PutMapping("updateJob/{id}")
    public ResponseEntity<JobDTO> updateJob(
            @PathVariable(name = "id") long id,
            @Valid @RequestBody JobDTO jobDTO
    ) {
        return new ResponseEntity<>(jobService.update(id, jobDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete job profile By Id REST API")
    @PreAuthorize("hasRole('RECRUITER')")
    @DeleteMapping("deleteJob/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable(name = "id") long id) {
        jobService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Find jobs by city REST API")
    @GetMapping("/findJobsByCity/{city}")
    public ResponseEntity<List<JobDTO>> findJobsByCity(@PathVariable(name = "city") City city) {
        return new ResponseEntity<>(jobService.findJobsByCity(city), HttpStatus.OK);
    }

    @ApiOperation(value = "Find jobs by skills REST API")
    @GetMapping("/findJobsBySkills/{skill}")
    public ResponseEntity<List<JobDTO>> findJobsBySkills(@PathVariable(name = "skill") String skill) {
        return new ResponseEntity<>(jobService.findJobsBySkills(skill), HttpStatus.OK);
    }

    @ApiOperation(value = "Find jobs by title REST API")
    @GetMapping("/findJobByTitle/{title}")
    public ResponseEntity<List<JobDTO>> findJobByTitle(@PathVariable(name = "title") String title) {
        return new ResponseEntity<>(jobService.findJobByTitle(title), HttpStatus.OK);
    }

    @ApiOperation(value = "Find jobs by title and company name REST API")
    @GetMapping("/findJobsByTitleAndCompanyName/{title}/{companyName}")
    public ResponseEntity<List<JobDTO>> findJobsByTitleAndCompanyName(
            @PathVariable(name = "title") String title,
            @PathVariable(name = "companyName") String companyName
    ) {
        return new ResponseEntity<>(jobService.findJobsByTitleAndCompanyName(title, companyName), HttpStatus.OK);
    }

    @ApiOperation(value = "Find job by Id REST API")
    @GetMapping("/job/{id}")
    public ResponseEntity<JobDTO> findJobById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(jobService.findById(id), HttpStatus.OK);
    }

}