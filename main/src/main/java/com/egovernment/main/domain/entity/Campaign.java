package com.egovernment.main.domain.entity;

import com.egovernment.main.domain.enums.CampaignType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "campaigns")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private CampaignType campaignType;
    @Column
    private Long campaignReferenceId;
    @Column
    private String title;
    @Column
    private String description;
    @ManyToOne
    private User from; //with role administrator
    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime endDate;
    @Column
    private String regionName;
    @Column
    private boolean isActive ;

}


