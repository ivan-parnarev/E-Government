package com.egovernment.kafka.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class CampaignFilteredDTO implements Serializable {

    private String campaignType;
    private String campaignTitle;
    private String regionName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
