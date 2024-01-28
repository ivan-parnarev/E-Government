package com.egovernment.main.domain.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignsOverviewDTO {

    private CampaignResultDTO GLOBAL;
    private CampaignResultDTO LOCAL;

}