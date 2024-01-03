package com.egovernment.egovbackend.validation;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.enums.ElectionType;
import com.egovernment.egovbackend.exceptions.InvalidCampaignException;
import com.egovernment.egovbackend.service.ElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CampaignValidator {

    private final ElectionService electionService;

    public boolean validateCampaignForCandidates(Campaign campaign) throws InvalidCampaignException {
        if (campaign == null) {
            throw new InvalidCampaignException("Campaign is null");
        }

        Long referenceId = campaign.getCampaignReferenceId();

        Optional<Election> optElection = electionService
                .getElectionByCampaignId(campaign.getId());

        if (optElection.isEmpty()) {
            throw new InvalidCampaignException("Election is null");
        }

        Election election = optElection.get();

        if ((election.getElectionType() == ElectionType.PARLIAMENT
                || election.getElectionType() == ElectionType.PRESIDENT)
                && referenceId == null) {
            return true;
        }

        if ((election.getElectionType() == ElectionType.MAYOR
                || election.getElectionType() == ElectionType.COUNCIL)
                && referenceId != null) {
            return true;
        }

        throw new InvalidCampaignException("Invalid campaign type or reference ID for adding candidates");
    }
}
