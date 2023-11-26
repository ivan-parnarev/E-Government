package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.enums.ElectionType;
import com.egovernment.egovbackend.domain.factory.election.ElectionFactory;
import com.egovernment.egovbackend.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final ElectionRepository electionRepository;
    private final ElectionFactory electionFactory = new ElectionFactory();

    public void initSampleElections(Campaign campaign) {
            Election election = this.launchElection(ElectionType.PARLIAMENT, campaign);
            this.electionRepository.save(election);
    }

    public Election launchElection(ElectionType electionType, Campaign campaign){
        return this.electionFactory.createElection(electionType, campaign);
    }

    public Optional<Election> getElectionById(long id) {
        return this.electionRepository.findById(id);
    }

    public Optional<Election> getElectionByCampaignId(Long campaignId) {
        return this.electionRepository.findByCampaignId(campaignId);
    }

    public Election createElection(CreateVotingCampaignDTO createVotingCampaignDTO, Campaign campaign) {
        ElectionType electionType = ElectionType.valueOf(createVotingCampaignDTO.getElectionType());
        Election election = launchElection(electionType, campaign);
        this.electionRepository.save(election);
        return election;
    }
}
