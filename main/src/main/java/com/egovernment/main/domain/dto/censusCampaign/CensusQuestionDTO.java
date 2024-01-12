package com.egovernment.main.domain.dto.censusCampaign;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CensusQuestionDTO {

    @NotBlank(message = "Question text is required and cannot be empty or null")
    private String text;
    @NotBlank(message = "Question category is required and cannot be empty or null")
    private String QuestionCategory;
    @Valid
    @NotEmpty(message = "Question answers are required and cannot be empty")
    private List<AnswerDTO> answers;
}
