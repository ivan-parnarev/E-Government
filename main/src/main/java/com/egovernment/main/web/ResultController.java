package com.egovernment.main.web;

import com.egovernment.main.domain.dto.result.*;
import com.egovernment.main.service.ResultService;
import com.egovernment.main.web.interfaces.ResultControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResultController implements ResultControllerInterface {

    private final ResultService resultService;

    @Override
    @GetMapping
    public ResponseEntity<CampaignsOverviewDTO> getResults() {

        this.resultService.updateResults();

        TotalRegionVotesDTO globalVote = TotalRegionVotesDTO.builder()
                .regionName("GLOBAL")
                .totalRegionVotes(123821L)
                .build();

        TotalRegionVotesDTO plovidvVote = TotalRegionVotesDTO.builder()
                .regionName("Plovdiv")
                .totalRegionVotes(123821L)
                .build();

        TotalRegionVotesDTO blagoevgradVote = TotalRegionVotesDTO.builder()
                .regionName("Blagoevgrad")
                .totalRegionVotes(123821L)
                .build();

        CandidateResultDTO candidateResultDTO = CandidateResultDTO.builder()
                .candidateName("Georgi Georgiev")
                .candidateParty("Partiya Georgi")
                .candidateVotes(819382L)
                .build();

        RegionVoteSummaryDTO globalCandidatesDTO = RegionVoteSummaryDTO.builder()
                .candidates(List.of(candidateResultDTO, candidateResultDTO))
                .regionName("GLOBAL")
                .build();

        RegionVoteSummaryDTO plovdivCandidatesDTO = RegionVoteSummaryDTO.builder()
                .candidates(List.of(candidateResultDTO, candidateResultDTO))
                .regionName("Plovdiv")
                .build();

        RegionVoteSummaryDTO blagoevgradCandidatesDTO = RegionVoteSummaryDTO.builder()
                .candidates(List.of(candidateResultDTO, candidateResultDTO))
                .regionName("Blagoevgrad")
                .build();

        CampaignResultDTO globalResults = CampaignResultDTO.builder()
                .campaignTitle("Global Campaign")
                .votes(List.of(globalVote))
                .date(LocalDateTime.now())
                .electionType("PRESIDENT")
                .campaignType("VOTING")
                .results(List.of(globalCandidatesDTO))
                .build();

        CampaignResultDTO localResults = CampaignResultDTO.builder()
                .campaignTitle("Local Campaign")
                .votes(List.of(plovidvVote, blagoevgradVote))
                .electionType("MAYOR")
                .campaignType("VOTING")
                .date(LocalDateTime.now())
                .results(List.of(plovdivCandidatesDTO, blagoevgradCandidatesDTO))
                .build();

        return ResponseEntity.ok(CampaignsOverviewDTO.builder()
                .LOCAL(localResults)
                .GLOBAL(globalResults)
                .build());
    }

}
