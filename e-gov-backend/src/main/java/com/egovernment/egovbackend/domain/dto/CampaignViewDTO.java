package com.egovernment.egovbackend.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignViewDTO {
    private int id;
    private String campaignType;
    private String campaignTopic;
    private LocalDateTime date;
    private int duration;
    private String answersJson;
}
