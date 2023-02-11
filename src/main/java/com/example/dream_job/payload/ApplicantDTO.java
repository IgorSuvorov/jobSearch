package com.example.dream_job.payload;

import com.example.dream_job.model.City;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
    @NotEmpty
    private long id;
    @NonNull
    private City city;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String title;
    @NotEmpty
    private Date updated;
    @NotEmpty
    List<String> skills;
}
