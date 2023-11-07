package com.egovernment.egovbackend.domain.entity;

import com.egovernment.egovbackend.domain.enums.CampaignType;
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
    @ManyToOne
    private User from; //with role administrator
    @Column
    private LocalDateTime date;
    @Column
    private int duration;
    @Column
    private String answersJson;

}
