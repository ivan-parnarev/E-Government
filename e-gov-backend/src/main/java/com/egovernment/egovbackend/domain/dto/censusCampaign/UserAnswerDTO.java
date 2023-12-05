package com.egovernment.egovbackend.domain.dto.censusCampaign;

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

    String questionText;
    String answer;
    QuestionCategory questionCategory;

}
