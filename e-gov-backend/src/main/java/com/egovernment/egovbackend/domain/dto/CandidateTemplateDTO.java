package com.egovernment.egovbackend.domain.dto;

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
    @NotBlank(message = "Candidate name is required but not found.")
    private String name;
    @NotBlank(message = "Candidate name is required but not found.")
    private String party;
    private Integer candidateNumber;
}
