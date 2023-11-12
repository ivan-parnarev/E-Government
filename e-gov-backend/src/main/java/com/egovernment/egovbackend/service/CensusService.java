package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.repository.CensusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CensusService {

    private final CensusRepository censusRepository;

    public boolean hasUserCensusedInCampaign(String userPin, Long campaignId){
        return this.censusRepository.censusExistsByUserPinAndCampaignId(userPin, campaignId);
    }

}
