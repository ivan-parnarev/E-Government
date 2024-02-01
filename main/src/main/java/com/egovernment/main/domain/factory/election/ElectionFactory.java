package com.egovernment.main.domain.factory.election;

import com.egovernment.main.domain.dto.voteCampaign.ElectionDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.enums.ElectionType;

public class ElectionFactory implements ElectionFactoryInterface{
    @Override
    public Election createElection(ElectionType electionType, Campaign campaign) {
        return Election.builder()
                .electionType(electionType)
                .campaign(campaign)
                .build();
    }
}
