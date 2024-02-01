package com.egovernment.main.domain.annotation.vote;

import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.service.VoteService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueVoteValidator implements ConstraintValidator<UniqueVoteConstraint, UserVotedInfoDTO> {

    private final VoteService voteService;
    @Override
    public void initialize(UniqueVoteConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserVotedInfoDTO userVotedInfoDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (userVotedInfoDTO == null) {
            return false;
        }

        return !this.voteService.hasUserVotedForCampaign(userVotedInfoDTO.getUserPin(), userVotedInfoDTO.getElectionId());
    }
}
