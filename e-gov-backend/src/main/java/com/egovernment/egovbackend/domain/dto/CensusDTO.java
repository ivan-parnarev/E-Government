package com.egovernment.egovbackend.domain.dto;

import com.egovernment.egovbackend.domain.annotation.census.UniqueCensusConstraint;
import com.egovernment.egovbackend.domain.template.AnswerCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UniqueCensusConstraint
public class CensusDTO {
    @NotBlank(message = "User PIN is required but it is not found.")
    private String pin;
    @NotNull(message = "Campaign id is required but it is not found.")
    private Long campaignId;
    private List<AnswerCategory> answers;

}
