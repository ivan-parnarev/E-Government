package com.egovernment.egovauth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignDTO {
    private String campaignType;
    private String campaignTitle;
    private String campaignDescription;
}
