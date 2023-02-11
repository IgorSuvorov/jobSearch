package com.example.dream_job.controller;
import com.example.dream_job.model.City;
import com.example.dream_job.payload.ApplicantDTO;
import com.example.dream_job.service.ApplicantService;
import com.example.dream_job.utilities.AppConstants;
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

    @ApiOperation(value = "Add new applicant REST API")
    @PreAuthorize("hasRole('APPLICANT')")
    @PostMapping("/addApplicant")
    public ResponseEntity<ApplicantDTO> addApplicant(@Valid @RequestBody ApplicantDTO applicantDTO) {
        ApplicantDTO savedApplicant = applicantService.save(applicantDTO);
        return new ResponseEntity<>(savedApplicant, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update applicant profile By Id REST API")
    @PreAuthorize("hasRole('APPLICANT')")
    @PutMapping("updateApplicant/{id}")
    public ResponseEntity<ApplicantDTO> updateApplicant(
            @PathVariable(name = "id") long id,
            @Valid @RequestBody ApplicantDTO applicantDTO
    ) {
        ApplicantDTO updatedApplicant = applicantService.update(id, applicantDTO);
        return new ResponseEntity<>(updatedApplicant, HttpStatus.OK);
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
        ApplicantDTO applicant = applicantService.findById(id);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all applicants REST API")
    @GetMapping
    public ResponseEntity<Page<ApplicantDTO>> getAllApplicants(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        Page<ApplicantDTO> applicants = applicantService.getAllApplicants(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @ApiOperation(value = "Find applicants by skills REST API")
    @GetMapping("/skills/{skill}")
    public ResponseEntity<Page<ApplicantDTO>> findApplicantsBySkills(@PathVariable("skill") String skill, Pageable pageable) {
        Page<ApplicantDTO> applicants = applicantService.findApplicantsBySkills(skill, pageable);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @ApiOperation(value = "Find applicants by title REST API")
    @GetMapping("/title/{title}")
    public ResponseEntity<Page<ApplicantDTO>> findApplicantsByTitle(
            @PathVariable("title") String title,
            @PageableDefault(size = 20, sort = "title") Pageable pageable
    ) {
        Page<ApplicantDTO> applicants = applicantService.findApplicantsByTitle(title, pageable);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @ApiOperation(value = "Find applicants by city REST API")
    @GetMapping("/city/{city}")
    public ResponseEntity<Page<ApplicantDTO>> findApplicantsByCity(@PathVariable("city") City city, Pageable pageable) {
        Page<ApplicantDTO> applicants = applicantService.findApplicantsByCity(city, pageable);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @ApiOperation(value = "Find applicants by title and city REST API")
    @GetMapping("/title/{title}/city/{city}")
    public ResponseEntity<Page<ApplicantDTO>> findApplicantsByTitleAndCity(
            @PathVariable("title") String title,
            @PathVariable("city") City city,
            Pageable pageable) {
        Page<ApplicantDTO> applicants = applicantService.findApplicantsByTitleAndCity(title, city, pageable);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }
}
