package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.repository.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;

    public boolean hasUserCensusedInCampaign(String userPin, Long campaignId){
        return this.userAnswerRepository.censusExistsByUserIdAndCampaignId(userPin, campaignId);
    }
}
