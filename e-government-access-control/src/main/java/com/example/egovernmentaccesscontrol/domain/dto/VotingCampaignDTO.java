package com.example.egovernmentaccesscontrol.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VotingCampaignDTO {
    private String campaignTitle;
}
