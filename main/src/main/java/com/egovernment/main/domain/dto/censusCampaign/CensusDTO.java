package com.egovernment.main.domain.dto.censusCampaign;

import com.egovernment.main.domain.annotation.census.UniqueCensusConstraint;
import com.egovernment.main.domain.annotation.pin.ValidUserPin;
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

//    @ValidUserPin
    @NotBlank(message = "User PIN is required but it is not found.")
    private String userPin;
    @NotNull(message = "Campaign id is required but it is not found.")
    private Long campaignId;
    private List<UserAnswerDTO> censusAnswers;

}
