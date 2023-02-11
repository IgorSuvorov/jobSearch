package com.example.dream_job.service;

import com.example.dream_job.exceptions.ApplicantNotFoundException;
import com.example.dream_job.model.Applicant;
import com.example.dream_job.model.City;
import com.example.dream_job.payload.ApplicantDTO;
import com.example.dream_job.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

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
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new ApplicantNotFoundException(id));

        applicant.setFirstName(applicantDTO.getFirstName());
        applicant.setLastName(applicantDTO.getLastName());
        applicant.setSkills(applicantDTO.getSkills());
        applicant.setCity(applicantDTO.getCity());
        Applicant updatedApplicant = applicantRepository.save(applicant);

        return mapEntityToDTO(updatedApplicant);
    }

    @Override
    public ApplicantDTO findById(long id) {
        Applicant applicant = applicantRepository.findById(id).
                orElseThrow(() -> new ApplicantNotFoundException(id));
        return mapEntityToDTO(applicant);
    }

    @Override
    public Page<ApplicantDTO> getAllApplicants(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Applicant> applicants = applicantRepository.findAll(pageable);

        return applicants.map(this::mapEntityToDTO);
    }

    @Override
    public Page<ApplicantDTO> findApplicantsBySkills(String skill, Pageable pageable) {
        Page<Applicant> applicants = applicantRepository.findApplicantsBySkills(skill, pageable);
        return applicants.map(this::mapEntityToDTO);
    }

    @Override
    public Page<ApplicantDTO> findApplicantsByCity(City city, Pageable pageable) {
        Page<Applicant> applicants = applicantRepository.findApplicantsByCity(city, pageable);
        List<ApplicantDTO> applicantDTOs = applicants.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
        return new PageImpl<>(applicantDTOs, pageable, applicants.getTotalElements());
    }

    @Override
    public Page<ApplicantDTO> findApplicantsByTitleAndCity(String title, City city, Pageable pageable) {
        Page<Applicant> applicants = applicantRepository.findApplicantsByTitleAndCity(title, city, pageable);
        List<ApplicantDTO> applicantDTOs = applicants.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
        return new PageImpl<>(applicantDTOs, pageable, applicants.getTotalElements());
    }


    @Override
    public Page<ApplicantDTO> findApplicantsByTitle(String title, Pageable pageable) {
        Page<Applicant> applicants = applicantRepository.findApplicantsByTitle(title, pageable);
        return applicants.map(this::mapEntityToDTO);
    }


    @Override
    public void delete(long id) {
        applicantRepository.deleteById(id);
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
