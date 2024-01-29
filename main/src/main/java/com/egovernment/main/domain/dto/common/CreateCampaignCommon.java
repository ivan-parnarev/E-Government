package com.egovernment.main.domain.dto.common;

import com.egovernment.main.domain.annotation.pin.ValidUserPin;
import com.egovernment.main.domain.enums.CampaignRegion;
import com.egovernment.main.domain.enums.CampaignType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Campaign type cannot be null.")
    private CampaignType campaignType;
    @NotBlank(message = "Campaign title is a required but not found.")
    private String campaignTitle;
    @NotBlank(message = "Campaign description is a required but not found.")
    private String campaignDescription;
    @NotBlank(message = "The pin of the creator is a required but not found.")
    @ValidUserPin
    private String creatorUserPin;
    @FutureOrPresent
    private LocalDateTime campaignStartDate;
    @Future
    private LocalDateTime campaignEndDate;
    private CampaignRegion campaignRegion;
}
