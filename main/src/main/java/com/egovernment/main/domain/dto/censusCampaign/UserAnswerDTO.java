package com.egovernment.main.domain.dto.censusCampaign;

import com.egovernment.main.domain.enums.QuestionCategory;
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
