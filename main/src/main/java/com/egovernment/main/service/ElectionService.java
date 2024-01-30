package com.egovernment.main.service;

import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.main.domain.dto.voteCampaign.CandidateDTO;
import com.egovernment.main.domain.dto.voteCampaign.ElectionDTO;
import com.egovernment.main.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.enums.ElectionType;
import com.egovernment.main.domain.factory.election.ElectionFactory;
import com.egovernment.main.repository.ElectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final CandidateService candidateService;
    private final ElectionRepository electionRepository;
    private final ElectionFactory electionFactory = new ElectionFactory();
    private final ModelMapper modelMapper;

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

    public List<Election> getElectionsByCampaignId(Long campaignId) {
        return this.electionRepository.findByCampaignId(campaignId);
    }

    public Election mapElectionDTOtoElection(ElectionDTO electionDTO) {
        return Election.builder()
                .electionRegion(electionDTO.getElectionRegion())
                .electionType(ElectionType.valueOf(electionDTO.getElectionType()))
                .build();
    }

    @Transactional
    public void createElectionsWithCandidates(List<ElectionDTO> electionsDTOList, Campaign campaign) {

        for (ElectionDTO electionDTO : electionsDTOList) {
            List<CandidateDTO> candidates = electionDTO.getCandidates();
            List<Candidate> candidatesToBeSaved = new ArrayList<>();

            Election election = mapElectionDTOtoElection(electionDTO);
            election.setCampaign(campaign);
            saveElection(election);

            for (CandidateDTO candidateDTO : candidates) {
                Candidate candidate = this.candidateService.mapCandidateDTOtoCandidate(candidateDTO);
                candidate.setElection(election);
                candidatesToBeSaved.add(candidate);
            }
            List<Candidate> savedCandidates = this.candidateService.saveCandidates(candidatesToBeSaved);

            election.setCandidateList(savedCandidates);
            saveElection(election);
        }
    }

    public void saveElection(Election election) {
        this.electionRepository.save(election);
    }

    public CampaignFilteredDTO mapElectionToCampaignFilteredDTO(Election election) {

        Campaign campaign = election.getCampaign();

        return CampaignFilteredDTO.builder()
                .campaignTitle(campaign.getTitle())
                .regionName(election.getElectionRegion())
                .campaignType(campaign.getCampaignType().toString())
                .electionId(election.getId())
                .electionType(election.getElectionType().toString())
                .build();

    }

    public VoteCampaignDTO getCampaignElectionById(Long electionId) {
        Optional<Election> optionalElection = this.electionRepository.findById(electionId);

        if(optionalElection.isEmpty()){
            System.out.println("optional not found");
            return null;
        }

        Election election = optionalElection.get();

        List<CandidateDTO> candidateDTOS = election
                .getCandidateList()
                .stream()
                .map(c -> this.modelMapper.map(c, CandidateDTO.class))
                .toList();

        Campaign campaign = election.getCampaign();

        VoteCampaignDTO voteCampaignDTO = VoteCampaignDTO.builder()
                .campaignType(campaign.getCampaignType().toString())
                .campaignTitle(campaign.getTitle())
                .campaignDescription(campaign.getDescription())
                .campaignStartDate(campaign.getStartDate())
                .campaignEndDate(campaign.getEndDate())
                .electionId(electionId)
                .electionType(election.getElectionType().toString())
                .electionCandidates(candidateDTOS)
                .build();

        return voteCampaignDTO;
    }

}
