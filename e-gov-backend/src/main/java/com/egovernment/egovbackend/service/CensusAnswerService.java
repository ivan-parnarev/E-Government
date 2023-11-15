package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.repository.CensusAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CensusAnswerService {

    private final CensusAnswerRepository censusAnswerRepository;

    public boolean hasUserCensusedInCampaign(String userPin, Long campaignId){
        return this.censusAnswerRepository.censusExistsByUserIdAndCampaignId(userPin, campaignId);
    }
}
