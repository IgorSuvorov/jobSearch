package com.example.dream_job.payload;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Igor Suvorov
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicantDTO {

    private long id;

    private String city;

    private String firstName;

    private String lastName;

    private String title;

    private Date updated;

    List<String> skills;
}
