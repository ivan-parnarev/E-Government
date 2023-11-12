package com.egovernment.egovbackend.domain.dto;

import com.egovernment.egovbackend.domain.annotation.vote.UniqueVoteConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@UniqueVoteConstraint
public class UserVotedInfoDTO {

    @NotBlank(message = "User PIN is required but it is not found.")
    private String pin;
    @NotNull(message = "Campaign id is required but it is not found.")
    private Long campaignId;
    @Valid
    @NotNull(message = "Information about the voted candidate is required but it is not found.")
    private CandidateTemplateDTO candidate;

}
