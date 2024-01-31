package com.egovernment.main.service;

import com.egovernment.main.client.KafkaConsumerClient;
import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.entity.Result;
import com.egovernment.main.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
