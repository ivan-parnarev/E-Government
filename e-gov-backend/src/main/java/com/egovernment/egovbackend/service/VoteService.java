package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public boolean hasUserVotedForCampaign(String userPin, Long campaignId){
       return this.voteRepository.existsByUserPinAndCampaignId(userPin, campaignId);
    }

}
