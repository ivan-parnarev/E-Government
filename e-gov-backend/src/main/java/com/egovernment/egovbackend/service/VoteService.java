package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.UserVotedInfoDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Vote;
import com.egovernment.egovbackend.repository.VoteRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final CampaignService campaignService;

    public boolean hasUserVotedForCampaign(String userPin, Long campaignId){
       return this.voteRepository.existsByUserPinAndCampaignId(userPin, campaignId);
    }

    public void saveVote(UserVotedInfoDTO voteDTO) {
        Optional<Campaign> optCampaign = this.campaignService.getCampaignById(voteDTO.getCampaignId());
        if(optCampaign.isPresent()){

            Campaign campaign = optCampaign.get();
            Gson gson = new Gson();
            String answerJson = gson.toJson(voteDTO.getCandidate());

            Vote vote = Vote.builder()
                    .campaign(campaign)
                    .userPin(voteDTO.getPin())
                    .answer(answerJson)
                    .date(LocalDate.now())
                    .build();

            this.voteRepository.save(vote);
        }

    }
}
