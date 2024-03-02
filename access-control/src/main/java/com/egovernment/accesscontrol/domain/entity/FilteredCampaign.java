package com.egovernment.accesscontrol.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "filtered_campaigns")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilteredCampaign extends BaseEntity {
    private String campaignType;
    private String campaignTitle;
    private Long campaignId;
    private Long electionId;
    private String electionType;
    private String regionName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
