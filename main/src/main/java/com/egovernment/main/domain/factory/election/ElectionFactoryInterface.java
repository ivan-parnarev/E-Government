package com.egovernment.egovbackend.domain.factory.election;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.enums.ElectionType;

public interface ElectionFactoryInterface {

    Election createElection(ElectionType electionType, Campaign campaign);

}
