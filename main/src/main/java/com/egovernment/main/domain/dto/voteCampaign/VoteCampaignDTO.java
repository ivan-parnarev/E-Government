package com.egovernment.egovbackend.domain.dto.voteCampaign;

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
public class VoteCampaignDTO {
    private String campaignType;
    private String campaignTitle;
    private String campaignDescription;
    private LocalDateTime campaignStartDate;
    private LocalDateTime campaignEndDate;
    private String electionType;
    private Long electionId;
    private List<CandidateTemplateDTO> electionCandidates;

}
