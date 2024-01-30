package com.egovernment.main.domain.dto.voteCampaign;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
    @NotBlank(message = "Candidate name is required but not found")
    private String candidateName;
    private String candidateParty;
    @NotBlank(message = "Candidate number is required, but not found")
    private Integer candidateNumber;
}
