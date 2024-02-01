package com.egovernment.main.domain.factory.election;

import com.egovernment.main.domain.dto.voteCampaign.ElectionDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.enums.ElectionType;

public interface ElectionFactoryInterface {

    Election createElection(ElectionType electionType, Campaign campaign);

}
