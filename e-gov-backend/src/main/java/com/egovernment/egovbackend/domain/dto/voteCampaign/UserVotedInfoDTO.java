package com.egovernment.egovbackend.domain.dto.voteCampaign;

import com.egovernment.egovbackend.domain.annotation.pin.ValidUserPin;
import com.egovernment.egovbackend.domain.annotation.vote.UniqueVoteConstraint;
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

    @ValidUserPin
    @NotBlank(message = "User PIN is required but it is not found.")
    private String userPin;
    @NotNull(message = "Election id is required but it is not found.")
    private Long electionId;
    @NotNull(message = "Candidate id is required but it is not found.")
    private Long candidateId;


}
