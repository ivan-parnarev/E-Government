package com.egovernment.egovbackend.domain.dto.censusCampaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CensusQuestionDTO {

    private Long id;
    private String text;
    private String QuestionCategory;
}
