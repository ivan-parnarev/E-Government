package com.example.egovernmentaccesscontrol.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFilteredDTO {

    private String id;
    private String campaignTitle;
    private String regionName;

}
