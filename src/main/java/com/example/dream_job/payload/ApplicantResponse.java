package com.example.dream_job.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Igor Suvorov
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {
    private List<ApplicantDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
