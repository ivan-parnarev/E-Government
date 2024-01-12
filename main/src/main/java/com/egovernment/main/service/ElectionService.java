package com.egovernment.main.service;

import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.enums.ElectionType;
import com.egovernment.main.domain.factory.election.ElectionFactory;
import com.egovernment.main.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final ElectionRepository electionRepository;
    private final ElectionFactory electionFactory = new ElectionFactory();

    public void initSampleElections(Campaign campaign) {
        if(this.electionRepository.count() == 0){
            Election election = this.launchElection(ElectionType.PARLIAMENT, campaign);
            this.electionRepository.save(election);
        }
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

    public Election createElection(String electionTypeString, Campaign campaign) {
        ElectionType electionType = ElectionType.valueOf(electionTypeString);
        Election election = launchElection(electionType, campaign);
        this.electionRepository.save(election);
        return election;
    }
}
