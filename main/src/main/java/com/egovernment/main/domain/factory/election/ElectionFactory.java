package com.egovernment.egovbackend.domain.factory.election;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.enums.ElectionType;

public class ElectionFactory implements ElectionFactoryInterface{
    @Override
    public Election createElection(ElectionType electionType, Campaign campaign) {
        return Election.builder()
                .electionType(electionType)
                .campaign(campaign)
                .build();
    }
}
