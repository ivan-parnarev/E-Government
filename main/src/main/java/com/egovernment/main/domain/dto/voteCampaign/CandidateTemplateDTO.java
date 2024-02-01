package com.egovernment.main.domain.dto.voteCampaign;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Candidate party is required but not found.")
    private String candidateParty;
    @NotNull(message = "Candidate number cannot be null")
    private Integer candidateNumber;
}
