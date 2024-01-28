package com.egovernment.main.domain.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CandidateResultDTO {

    private String candidateName;
    private String candidateParty;
    private Long candidateVotes;

}