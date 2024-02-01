package com.egovernment.main.domain.dto.voteCampaign;

import com.egovernment.main.domain.dto.common.CreateCampaignCommon;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Election list is required but not found")
    private List<ElectionDTO> elections;
}
