package com.egovernment.egovbackend.domain.dto.censusCampaignDTO;

import com.egovernment.egovbackend.domain.enums.QuestionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerDTO {

    Long questionId;
    String answer;
    QuestionCategory questionCategory;

}
