package com.egovernment.kafka.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CensusCampaignFilteredDTO extends CampaignFilteredDTO{
    private Long campaignId;
}
