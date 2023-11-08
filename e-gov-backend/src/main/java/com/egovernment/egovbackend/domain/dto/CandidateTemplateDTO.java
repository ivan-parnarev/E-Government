package com.egovernment.egovbackend.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTemplateDTO {

    private int id;
    @Min(value = 1, message = "Candidate number cannot be lower that 1.")
    private int number;
    @NotBlank(message = "Candidate name is required but not found.")
    private String name;
}
