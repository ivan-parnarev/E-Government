package com.egovernment.main.domain.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegionVoteSummaryDTO {

    private String regionName;
    private List<CandidateResultDTO> candidates;

}