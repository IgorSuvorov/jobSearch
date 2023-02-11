package com.example.dream_job.controller;
import com.example.dream_job.model.City;
import com.example.dream_job.payload.ApplicantDTO;
import com.example.dream_job.service.ApplicantService;
import com.example.dream_job.utilities.AppConstants;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Igor Suvorov
 */

@Controller
public class ApplicantController {
    private final ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @ApiOperation(value = "Get All Applicants REST API")
    @GetMapping("/applicants")
    public ApplicantResponse getAllApplicants(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return applicantService.getAllApplicants(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "Add new applicant REST API")
    @PreAuthorize("hasRole('APPLICANT')")
    @PostMapping("/addApplicant")
    public ResponseEntity<ApplicantDTO> addApplicant(@Valid @RequestBody ApplicantDTO applicantDTO) {
        return new ResponseEntity<>(applicantService.save(applicantDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update applicant profile By Id REST API")
    @PreAuthorize("hasRole('APPLICANT')")
    @PutMapping("updateApplicant/{id}")
    public ResponseEntity<ApplicantDTO> updateApplicant(
            @PathVariable(name = "id") long id,
            @Valid @RequestBody ApplicantDTO applicantDTO
    ) {
        return new ResponseEntity<>(applicantService.update(id, applicantDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete applicant profile By Id REST API")
    @PreAuthorize("hasRole('APPLICANT')")
    @DeleteMapping("deleteApplicant/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable(name = "id") long id) {
        applicantService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Get applicant profile By Id REST API")
    @GetMapping("/getApplicantById/{id}")
    public ResponseEntity<ApplicantDTO> getApplicantById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(applicantService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Find applicants by first and last name REST API")
    @GetMapping("/findApplicantsByFirstAndLastName")
    public ResponseEntity<ApplicantDTO> findApplicantsByFirstAndLastName(
            @RequestParam(value = "first") String first,
            @RequestParam(value = "last") String last
    ) {
        return new ResponseEntity<>(applicantService.findApplicantsByFirstAndLastName(first, last), HttpStatus.OK);
    }

    @ApiOperation(value = "Find applicants by skills REST API")
    @GetMapping("/findApplicantsBySkills")
    public ResponseEntity<ApplicantDTO> findApplicantsBySkills(
            @RequestParam(value = "skill") String skill
    ) {
        return new ResponseEntity<>(applicantService.findApplicantsBySkills(skill), HttpStatus.OK);
    }

    @ApiOperation(value = "Find applicants by city REST API")
    @GetMapping("/findApplicantsByCity")
    public ResponseEntity<ApplicantDTO> findApplicantsByCity(
            @RequestParam(value = "city") City city
    ) {
        return new ResponseEntity<>(applicantService.findApplicantsByCity(city), HttpStatus.OK);
    }

}
