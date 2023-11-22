package com.egovernment.egovbackend.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignViewDTO {
    private int id;
    private String campaignTitle;

}
