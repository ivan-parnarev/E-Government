package com.example.accesscontrol.domain.dto;

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
    private String campaignTitle;
    private String regionName;
    private String campaignType;

}
