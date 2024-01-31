package com.egovernment.egovbackend.domain.dto.voteCampaign;

import com.egovernment.egovbackend.domain.dto.common.CreateCampaignCommon;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVotingCampaignDTO extends CreateCampaignCommon {
    @NotBlank(message = "Election type is a required but not found.")
    private String electionType;
    private CandidateRegionTemplateDTO candidates;

}
