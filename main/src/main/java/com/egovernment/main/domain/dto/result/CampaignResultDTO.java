package com.egovernment.main.domain.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignResultDTO {

    private String campaignTitle;
    private LocalDateTime date;
    private List<TotalRegionVotesDTO> votes;
    private List<RegionVoteSummaryDTO> results;

}
