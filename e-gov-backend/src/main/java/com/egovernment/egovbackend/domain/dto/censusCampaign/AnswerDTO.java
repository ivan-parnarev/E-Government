package com.egovernment.egovbackend.domain.dto.censusCampaign;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AnswerDTO {
    @NotBlank(message = "Answer text is required and cannot be empty or null")
    private String answerText;
}
