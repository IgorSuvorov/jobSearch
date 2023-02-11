package com.example.dream_job.payload;

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
public class JobDTO {
    @NotEmpty
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String companyName;
    @NonNull
    private String city;
    @NotBlank
    private String description;
    @NotEmpty
    private Date updated;
    @NotEmpty
    private List<String> skills;
}
