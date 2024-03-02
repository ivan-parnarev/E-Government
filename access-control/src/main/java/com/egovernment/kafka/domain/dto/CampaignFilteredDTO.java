package com.egovernment.kafka.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignFilteredDTO implements Serializable {

    private String campaignType;
    private String campaignTitle;
    private Long campaignId;
    private Long electionId;
    private String electionType;
    private String regionName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
