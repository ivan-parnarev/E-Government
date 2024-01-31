package com.egovernment.egovbackend.domain.dto.common;

import com.egovernment.egovbackend.domain.annotation.pin.ValidUserPin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignCommon {
    @NotBlank(message = "Campaign type is a required but not found.")
    private String campaignType;
    @NotBlank(message = "Campaign title is a required but not found.")
    private String campaignTitle;
    @NotBlank(message = "Campaign description is a required but not found.")
    private String campaignDescription;
//    @NotBlank(message = "The pin of the creator is a required but not found.")
//    @ValidUserPin
    private String creatorUserPin;
    @FutureOrPresent
    private LocalDateTime campaignStartDate;
    @Future
    private LocalDateTime campaignEndDate;
    private String campaignRegion;
    private Long campaignReferenceId;
}
