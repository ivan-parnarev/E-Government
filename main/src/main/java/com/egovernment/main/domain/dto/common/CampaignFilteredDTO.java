package com.egovernment.main.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFilteredDTO implements Serializable {

    private Long campaignId;
    private String campaignTitle;
    private String regionName;
    private String campaignType;

}