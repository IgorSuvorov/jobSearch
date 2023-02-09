package com.example.dream_job.service;

import com.example.dream_job.model.Applicant;
import com.example.dream_job.model.City;
import com.example.dream_job.payload.ApplicantDTO;
import com.example.dream_job.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.Optional;

/**
 * @author Igor Suvorov
 */

@Service
public class ApplicantServiceImpl implements ApplicantService {
    private ApplicantRepository applicantRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ModelMapper modelMapper) {
        this.applicantRepository = applicantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApplicantDTO save(ApplicantDTO applicantDTO) {
        return mapEntityToDTO(applicantRepository.save(mapDTOToEntity(applicantDTO)));
    }

    @Override
    public ApplicantDTO update(long id, ApplicantDTO applicantDTO) {
        return null;
    }

    @Override
    public Optional<Applicant> findApplicantsByFirstAndLastName(String first, String last) {
        return Optional.empty();
    }

    @Override
    public Optional<Applicant> findApplicantsBySkills(String skill) {
        return Optional.empty();
    }

    @Override
    public Optional<Applicant> findApplicantsByCity(City city) {
        return Optional.empty();
    }

    @Override
    public Object findAll() {
        return null;
    }

    private ApplicantDTO mapEntityToDTO(Applicant applicant) {
        ApplicantDTO applicantDTO = modelMapper.map(applicant, ApplicantDTO.class);
        return applicantDTO;
    }

    private Applicant mapDTOToEntity(ApplicantDTO applicantDTO) {
        Applicant applicant = modelMapper.map(applicantDTO, Applicant.class);
        return applicant;
    }
}
