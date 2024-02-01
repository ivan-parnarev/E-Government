package com.egovernment.main.domain.dto.censusCampaign;

import com.egovernment.main.domain.dto.common.CreateCampaignCommon;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class CreateCensusCampaignDTO extends CreateCampaignCommon {
    @Valid
    List<CensusQuestionDTO> questions;

}
