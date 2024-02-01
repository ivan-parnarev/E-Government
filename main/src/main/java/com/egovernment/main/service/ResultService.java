package com.egovernment.main.service;

import com.egovernment.main.client.KafkaConsumerClient;
import com.egovernment.main.domain.dto.voteCampaign.CandidateDTO;
import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.ElectionDTO;
import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.entity.Result;
import com.egovernment.main.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final KafkaConsumerClient kafkaConsumerClient;
    private final ElectionService electionService;
    private final CampaignService campaignService;
    private final ModelMapper modelMapper;

    public void updateResults() {

        List<UserVotedInfoDTO> results = kafkaConsumerClient
                .getCampaignResults().getBody();

        List<Campaign> activeCampaigns = campaignService.getAllActiveCampaigns();

        for (Campaign c : activeCampaigns) {

            List<Election> campaignElections = this.electionService
                    .getElectionsByCampaignId(c.getId());

            for (Election election : campaignElections) {

                List<Candidate> candidates = election.getCandidateList();

                for (Candidate candidate : candidates) {

                    List<UserVotedInfoDTO> candidatesPerElectionVotes = results
                            .stream()
                            .filter(r -> Objects.equals(r.getElectionId(), election.getId())
                                    && Objects.equals(r.getCandidateId(), candidate.getId()))
                            .toList();

                    Optional<Result> optCandidateResult = this.resultRepository
                            .findByElectionIdAndCandidateId(election.getId(),
                                    candidate.getId());

                    Result result = optCandidateResult.orElseGet(() -> Result
                            .builder()
                            .candidate(candidate)
                            .election(election)
                            .build());

                    result.setVotesCount((long) candidatesPerElectionVotes.size());

                    this.resultRepository.save(result);

                }


            }
        }
    }

    public List<CreateVotingCampaignDTO> getCampaignsResult(){

        List<Campaign> votingCampaigns = this.campaignService.getAllVotingCampaigns();

        List<CreateVotingCampaignDTO> votingCampaignsResult = new ArrayList<>();

        for (Campaign currentCampaign : votingCampaigns) {

            CreateVotingCampaignDTO votingCampaignDTO = this.modelMapper
                    .map(currentCampaign, CreateVotingCampaignDTO.class);
            votingCampaignDTO.setElections(new ArrayList<>());

            List<Election> elections = this.electionService
                    .getElectionsByCampaignId(currentCampaign.getId());

            for (Election election : elections) {

                ElectionDTO electionDTO = ElectionDTO
                        .builder()
                        .electionRegion(election.getElectionRegion())
                        .electionType(election.getElectionType().toString())
                        .build();

                List<CandidateDTO> candidateDtoList = new ArrayList<>();

                Long totalElectionVotes = 0L;

                for (Candidate candidate : election.getCandidateList()) {

                    CandidateDTO candidateDTO = CandidateDTO
                            .builder()
                            .candidateId(candidate.getId())
                            .candidateName(candidate.getName())
                            .candidateParty(candidate.getParty())
                            .candidateNumber(candidate.getCandidateNumber())
                            .build();

                    Optional<Result> optResult = this.resultRepository
                            .findByElectionIdAndCandidateId(election.getId(), candidate.getId());
                    Long candidateVotes = 0L;

                    if(optResult.isPresent()){
                        candidateVotes = optResult.get().getVotesCount();
                    }

                    candidateDTO.setCandidateVotes(candidateVotes);
                    candidateDtoList.add(candidateDTO);
                    totalElectionVotes += candidateVotes;
                }

                electionDTO.setCandidates(candidateDtoList);
                electionDTO.setTotalVotes(totalElectionVotes);
                votingCampaignDTO.getElections().add(electionDTO);
            }

            votingCampaignsResult.add(votingCampaignDTO);

        }
        return votingCampaignsResult;
    }

}
