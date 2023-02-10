package com.example.dream_job.controller;
import com.example.dream_job.model.Applicant;
import com.example.dream_job.payload.ApplicantDTO;
import com.example.dream_job.service.ApplicantService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/applicants")
    public String candidates(Model model) {
        model.addAttribute("applicants", applicantService.findAll());
        return "applicants";
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

}
