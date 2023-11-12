package com.egovernment.egovbackend.domain.annotation.vote;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.service.VoteService;
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

        return !this.voteService.hasUserVotedForCampaign(userVotedInfoDTO.getPin(), userVotedInfoDTO.getCampaignId());
    }
}
