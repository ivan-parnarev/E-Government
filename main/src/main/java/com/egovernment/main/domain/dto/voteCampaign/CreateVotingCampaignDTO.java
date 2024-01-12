package com.egovernment.main.domain.dto.voteCampaign;

import com.egovernment.main.domain.dto.common.CreateCampaignCommon;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVotingCampaignDTO extends CreateCampaignCommon {
    @NotBlank(message = "Election type is a required but not found.")
    private String electionType;
    private CandidateRegionTemplateDTO candidates;


}
