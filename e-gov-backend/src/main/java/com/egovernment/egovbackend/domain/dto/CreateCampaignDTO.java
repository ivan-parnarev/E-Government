package com.egovernment.egovbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignDTO {

    private String campaignType;
    private String campaignTitle;
    private String campaignDescription;
    private String creatorUserPin;
    private LocalDateTime campaignStartDate;
    private LocalDateTime campaignEndDate;
    private String electionType;
    private List<CandidateTemplateDTO> candidates;


}
