package com.example.dream_job.controller;
import com.example.dream_job.model.Applicant;
import com.example.dream_job.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/addApplicant")
    public String addApplicant(Model model) {
        model.addAttribute("applicants", new Applicant(0, "Fill the name field"));
        return "addApplicant";
    }

    @GetMapping("/formUpdateApplicant/{ApplicantId}")
    public String formUpdateApplicant (Model model, @PathVariable ("ApplicantId") int id) {
        model.addAttribute("candidate", applicantService.findById(id));
        return "updateApplicant";
    }

    @PostMapping("/updateCandidate")
    public String updateApplicant(@ModelAttribute Applicant candidate) {
        applicantService.saveCandidate(candidate);
        return "redirect:/applicants";
    }

    @PostMapping("/createCandidate")
    public String createApplicant(@ModelAttribute Applicant applicant) {
        applicantService.saveCandidate(applicant);
        return "redirect:/applicants";
    }
}
