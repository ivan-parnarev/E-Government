package com.egovernment.accesscontrol.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFilteredDTO {

    private Long campaignId;
    private String campaignTitle;//added campaign title and type
    private String regionName;
    private String campaignType;

}
