package com.egovernment.egovbackend.domain.dto.censusCampaignDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CensusQuestionDTO {

    private String text;
    private String QuestionCategory;
}
