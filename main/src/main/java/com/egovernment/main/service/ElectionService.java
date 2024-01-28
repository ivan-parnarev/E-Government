package com.egovernment.main.service;

import com.egovernment.main.domain.dto.voteCampaign.CandidateDTO;
import com.egovernment.main.domain.dto.voteCampaign.ElectionDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.enums.ElectionType;
import com.egovernment.main.domain.factory.election.ElectionFactory;
import com.egovernment.main.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final CandidateService candidateService;
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

    public Election mapElectionDTOtoElection(ElectionDTO electionDTO) {
        return Election.builder()
                .electionRegion(electionDTO.getElectionRegion())
                .electionType(ElectionType.valueOf(electionDTO.getElectionType()))
                .build();
    }

    public void createElectionsWithCandidates(List<ElectionDTO> electionsDTOList, Campaign campaign) {

        for (ElectionDTO electionDTO : electionsDTOList) {
            List<CandidateDTO> candidates = electionDTO.getCandidates();
            List<Candidate> candidatesInElection = new ArrayList<>();

            for (CandidateDTO candidateDTO : candidates) {
                Candidate candidate = this.candidateService.mapCandidateDTOtoCandidate(candidateDTO);
                candidatesInElection.add(candidate);
            }
            this.candidateService.saveCandidates(candidatesInElection);

            Election election = mapElectionDTOtoElection(electionDTO);
            election.setCampaign(campaign);
            election.setCandidateList(candidatesInElection);
            saveElection(election);
        }
    }

    public void saveElection(Election election) {
        this.electionRepository.save(election);
    }
}
