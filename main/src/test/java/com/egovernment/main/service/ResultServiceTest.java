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
import com.egovernment.main.domain.enums.ElectionType;
import com.egovernment.main.repository.ResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;
    @Mock
    private KafkaConsumerClient kafkaConsumerClient;
    @Mock
    private ElectionService electionService;
    @Mock
    private CampaignService campaignService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ResultService resultService;
    private final Long ID = 1L;
    private final String TEST_VOTING_CAMPAIGN_TITLE = "VotingCampaignTitle";
    private final String TEST_ELECTION_REGION = "TestElectionRegion";
    private final String TEST_ELECTION_TYPE = "MAYOR";
    private final String CANDIDATE_NAME = "Test Name";
    private final String CANDIDATE_PARTY = "Test Name";
    private final int CANDIDATE_NUMBER = 1;
    private UserVotedInfoDTO TEST_VOTE = UserVotedInfoDTO.builder()
            .candidateId(ID)
            .electionId(ID)
            .build();
    private Campaign TEST_VOTING_CAMPAIGN = Campaign.builder()
            .title(TEST_VOTING_CAMPAIGN_TITLE)
            .build();
    private Candidate TEST_CANDIDATE = Candidate.builder()
            .name(CANDIDATE_NAME)
            .candidateNumber(CANDIDATE_NUMBER)
            .party(CANDIDATE_PARTY)
            .build();
    private Election TEST_ELECTION = Election.builder()
            .candidateList(List.of(TEST_CANDIDATE))
            .electionRegion(TEST_ELECTION_REGION)
            .electionType(ElectionType.MAYOR)
            .build();
    private Result TEST_RESULT = Result.builder()
            .election(TEST_ELECTION)
            .candidate(TEST_CANDIDATE)
            .votesCount(1L)
            .build();

    @Test
    public void testUpdateResultsForExistingCampaign() {
        TEST_VOTING_CAMPAIGN.setId(ID);
        TEST_ELECTION.setId(ID);
        TEST_CANDIDATE.setId(ID);
        when(kafkaConsumerClient.getCampaignResults()).thenReturn(ResponseEntity.ok(List.of(TEST_VOTE)));

        when(this.campaignService.getAllActiveCampaigns()).thenReturn(List.of(TEST_VOTING_CAMPAIGN));
        when(this.electionService.getElectionsByCampaignId(ID)).thenReturn(List.of(TEST_ELECTION));
        when(this.resultRepository.findByElectionIdAndCandidateId(ID, ID)).thenReturn(Optional.of(TEST_RESULT));

        this.resultService.updateResults();

        verify(this.resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    public void testGetCampaignResults(){
        TEST_VOTING_CAMPAIGN.setId(ID);
        TEST_ELECTION.setId(ID);
        TEST_CANDIDATE.setId(ID);

        CandidateDTO candidateDTO = CandidateDTO.builder()
                .candidateVotes(1L)
                .build();

        ElectionDTO electionDTO = ElectionDTO
                .builder()
                .electionRegion(TEST_ELECTION_REGION)
                .electionType(TEST_ELECTION_TYPE)
                .candidates(List.of(candidateDTO))
                .totalVotes(1L)
                .build();

        CreateVotingCampaignDTO votingCampaignDTO = CreateVotingCampaignDTO.builder()
                .campaignTitle(TEST_VOTING_CAMPAIGN_TITLE)
                .elections(List.of(electionDTO))
                .build();

        when(campaignService.getAllVotingCampaigns()).thenReturn(List.of(TEST_VOTING_CAMPAIGN));
        when(this.modelMapper.map(any(Campaign.class), eq(CreateVotingCampaignDTO.class))).thenReturn(votingCampaignDTO);

        when(this.electionService.getElectionsByCampaignId(ID)).thenReturn(List.of(TEST_ELECTION));
        when(this.resultRepository.findByElectionIdAndCandidateId(ID, ID)).thenReturn(Optional.of(TEST_RESULT));

        List<CreateVotingCampaignDTO> result = this.resultService.getCampaignsResult();

        assertEquals(1, result.size());
        assertEquals(votingCampaignDTO.getCampaignTitle(), result.get(0).getCampaignTitle());
        assertEquals(votingCampaignDTO.getElections().size(), result.get(0).getElections().size());
        assertEquals(votingCampaignDTO.getElections().get(0).getTotalVotes(), result.get(0).getElections().get(0).getTotalVotes());
        assertEquals(votingCampaignDTO.getElections().get(0).getCandidates().get(0).getCandidateVotes(),
                result.get(0).getElections().get(0).getCandidates().get(0).getCandidateVotes());
    }
}
