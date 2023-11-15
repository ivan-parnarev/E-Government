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

    private int candidateId;
    @NotBlank(message = "Candidate name is required but not found.")
    private String candidateName;
    @NotBlank(message = "Candidate name is required but not found.")
    private String candidateParty;
    private Integer candidateNumber;
}
