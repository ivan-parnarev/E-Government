package com.egovernment.main.domain.dto.censusCampaign;

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
public class CensusCampaignDTO {

    private Long campaignId;
    private String campaignType;
    private String campaignTitle;
    private String campaignDescription;
    private LocalDateTime campaignStartDate;
    private LocalDateTime campaignEndDate;
    private List<CensusQuestionDTO> censusQuestions;
}
