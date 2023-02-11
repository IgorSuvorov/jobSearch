package com.example.dream_job.controller;

import com.example.dream_job.model.City;
import com.example.dream_job.payload.JobDTO;
import com.example.dream_job.service.JobService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.dream_job.utilities.AppConstants;

/**
 * @author Igor Suvorov
 */

@Controller
public class JobController {
    // закончить используя Model model и для аппликантов тоже!!!
    // добавить paging не только для всех, но и для результатов операций поиска
    // использовать Page (org.springframework.data.domain.Page) вместо JobResponse - переделать везде где используется
    // если использовать ResponseEntity, то Model model не нужно
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

//    @ApiOperation(value = "Get All Jobs REST API")
//    @GetMapping
//    public String getAllJobs(
//            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
//            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
//            Model model
//    ) {
//        JobResponse jobResponse = jobService.getAllJobs(pageNo, pageSize, sortBy, sortDir);
//        model.addAttribute("jobs", jobResponse.getContent());
//        return "jobs";
//    }

    @GetMapping
    public ResponseEntity<Page<JobDTO>> getAllJobs(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        Page<JobDTO> jobs = jobService.getAllJobs(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }


    @ApiOperation(value = "Add new job REST API")
    @PreAuthorize("hasRole('EMPLOYER')")
    @PostMapping("/addJob")
    public ResponseEntity<JobDTO> addJob(@Valid @RequestBody JobDTO jobDTO, Model model) {
        JobDTO savedJob = jobService.save(jobDTO);
        model.addAttribute("job", savedJob);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update job By Id REST API")
    @PreAuthorize("hasRole('EMPLOYER')")
    @PutMapping("updateJob/{id}")
    public ResponseEntity<JobDTO> updateJob(
            @PathVariable(name = "id") long id,
            @Valid @RequestBody JobDTO jobDTO
    ) {
        JobDTO updatedJob = jobService.update(id, jobDTO);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete job By Id REST API")
    @PreAuthorize("hasRole('EMPLOYER')")
    @DeleteMapping("deleteJob/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable(name = "id") long id) {
        jobService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Find jobs by city REST API")
    @GetMapping("/jobs/city/{city}")
    public ResponseEntity<Page<JobDTO>> findJobsByCity(@PathVariable("city") City city, Pageable pageable) {
        Page<JobDTO> jobs = jobService.findJobsByCity(city, pageable);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @ApiOperation(value = "Find jobs by skill REST API")
    @GetMapping("/jobs/skills/{skill}")
    public ResponseEntity<Page<JobDTO>> findJobsBySkills(@PathVariable("skill") String skill, Pageable pageable) {
        Page<JobDTO> jobs = jobService.findJobsBySkills(skill, pageable);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }


    @ApiOperation(value = "Find job by title REST API")
    @GetMapping("/jobs/title/{title}")
    public ResponseEntity<Page<JobDTO>> findJobsByTitle(
            @PathVariable("title") String title,
            @PageableDefault(size = 20, sort = "title") Pageable pageable
    ) {
        Page<JobDTO> jobs = jobService.findJobsByTitle(title, pageable);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }


    @ApiOperation(value = "Find jobs by title and company name REST API")
    @GetMapping("/jobs/title/{title}/city/{city}")
    public ResponseEntity<Page<JobDTO>> findJobsByTitleAndCity(
            @PathVariable("title") String title,
            @PathVariable("city") City city,
            Pageable pageable
    ) {
        Page<JobDTO> jobs = jobService.findJobsByTitleAndCity(title, city, pageable);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }


    @ApiOperation(value = "Find job by ID REST API")
    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> findJobById(@PathVariable("id") long id, Model model) {
        JobDTO job = jobService.findJobById(id);
        model.addAttribute("job", job);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
}