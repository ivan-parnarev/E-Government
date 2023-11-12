package com.egovernment.egovbackend.domain.annotation.census;

import com.egovernment.egovbackend.domain.dto.CensusDTO;
import com.egovernment.egovbackend.service.CensusService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueCensusValidator implements ConstraintValidator<UniqueCensusConstraint, CensusDTO> {

    private final CensusService censusService;

    @Override
    public void initialize(UniqueCensusConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CensusDTO censusDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (censusDTO == null) {
            return false;
        }

        return !this.censusService.hasUserCensusedInCampaign(censusDTO.getPin(), censusDTO.getCampaignId());
    }
}
