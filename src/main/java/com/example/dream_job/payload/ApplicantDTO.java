package com.example.dream_job.payload;

import com.example.dream_job.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Igor Suvorov
 */

@Data
@AllArgsConstructor
@Getter
@Setter
public class ApplicantDTO {
    private long id;
    private City city;
    private String firstName;
    private String lastName;
    private Date updated;
    List<String> skills;
}
