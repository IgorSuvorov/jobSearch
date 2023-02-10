package com.example.dream_job.payload;

import com.example.dream_job.model.City;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

/**
 * @author Igor Suvorov
 */

@Data
@AllArgsConstructor
@Getter
@Setter
public class JobDTO {
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String companyName;
    @NonNull
    private City city;
    @NotBlank
    private String description;
    @NotEmpty
    private List<String> skills;
}