package com.egovernment.egovbackend.domain.dto.voteCampaign;

import com.egovernment.egovbackend.domain.annotation.pin.ValidUserPin;
import com.egovernment.egovbackend.domain.dto.common.CreateCampaignCommon;
import com.egovernment.egovbackend.domain.dto.voteCampaign.CandidateTemplateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVotingCampaignDTO extends CreateCampaignCommon {

//    @NotBlank(message = "Campaign type is a required but not found.")
//    private String campaignType;
//    @NotBlank(message = "Campaign title is a required but not found.")
//    private String campaignTitle;
//    @NotBlank(message = "Campaign description is a required but not found.")
//    private String campaignDescription;
//    @NotBlank(message = "The pin of the creator is a required but not found.")
//    @ValidUserPin
//    private String creatorUserPin;
//    @FutureOrPresent
//    private LocalDateTime campaignStartDate;
//    @Future
//    private LocalDateTime campaignEndDate;
    @NotBlank(message = "Election type is a required but not found.")
    private String electionType;
    @Valid
    private List<CandidateTemplateDTO> candidates;


}
