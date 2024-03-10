package com.egovernment.kafka.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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