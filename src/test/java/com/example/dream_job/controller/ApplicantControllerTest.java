package com.example.dream_job.controller;

import com.example.dream_job.payload.ApplicantDTO;
import com.example.dream_job.service.applicant.ApplicantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicantControllerTest {

    @Mock
    private ApplicantService applicantService;

    @InjectMocks
    private ApplicantController applicantController;

    private ApplicantDTO applicantDTO;
    private Page<ApplicantDTO> applicants;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        applicantDTO = new ApplicantDTO();
        applicantDTO.setId(1L);
        applicantDTO.setFirstName("John");
        applicantDTO.setLastName("Doe");
        List<String> skills = new ArrayList<>();
        skills.add("java");
        applicantDTO.setSkills(skills);

        List<ApplicantDTO> applicantDTOList = new ArrayList<>();
        applicantDTOList.add(applicantDTO);

        applicants = new PageImpl<>(applicantDTOList);

        pageable = PageRequest.of(0, 20);
    }

    @Test
    void addApplicant() {
        when(applicantService.save(any(ApplicantDTO.class))).thenReturn(applicantDTO);

        ResponseEntity<ApplicantDTO> result = applicantController.addApplicant(applicantDTO);

        verify(applicantService, times(1)).save(any(ApplicantDTO.class));
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(applicantDTO, result.getBody());
    }

    @Test
    void updateApplicant() {
        when(applicantService.update(anyLong(), any(ApplicantDTO.class))).thenReturn(applicantDTO);

        ResponseEntity<ApplicantDTO> result = applicantController.updateApplicant(1L, applicantDTO);

        verify(applicantService, times(1)).update(anyLong(), any(ApplicantDTO.class));
    }

    @Test
    public void deleteApplicant_ValidId_ShouldDeleteApplicant() {
        long id = 1;
        doNothing().when(applicantService).delete(id);
        ResponseEntity<Void> response = applicantController.deleteApplicant(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(applicantService, times(1)).delete(id);
    }

    @Test
    public void getApplicantById_ValidId_ShouldReturnApplicant() {
        long id = 1;
        ApplicantDTO applicant = new ApplicantDTO();
        when(applicantService.findById(id)).thenReturn(applicant);
        ResponseEntity<ApplicantDTO> response = applicantController.getApplicantById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(applicant, response.getBody());
        verify(applicantService, times(1)).findById(id);
    }

    @Test
    public void getAllApplicants_ShouldReturnAllApplicants() {
        int pageNo = 0;
        int pageSize = 20;
        String sortBy = "id";
        String sortDir = "asc";
        Page<ApplicantDTO> applicants = new PageImpl<>(Collections.emptyList());
        when(applicantService.getAllApplicants(pageNo, pageSize, sortBy, sortDir)).thenReturn(applicants);
        ResponseEntity<Page<ApplicantDTO>> response = applicantController.getAllApplicants(pageNo, pageSize, sortBy, sortDir);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(applicants, response.getBody());
        verify(applicantService, times(1)).getAllApplicants(pageNo, pageSize, sortBy, sortDir);
    }

    @Test
    public void findApplicantsBySkills_ValidSkill_ShouldReturnApplicantsWithGivenSkill() {
        String skill = "Java";
        Pageable pageable = PageRequest.of(0, 20);
        Page<ApplicantDTO> applicants = new PageImpl<>(Collections.emptyList());
        when(applicantService.findApplicantsBySkills(skill, pageable)).thenReturn(applicants);
        ResponseEntity<Page<ApplicantDTO>> response = applicantController.findApplicantsBySkills(skill, pageable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(applicants, response.getBody());
        verify(applicantService, times(1)).findApplicantsBySkills(skill, pageable);
    }

}