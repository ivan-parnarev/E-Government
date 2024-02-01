package com.egovernment.main.service;

import com.egovernment.main.domain.dto.voteCampaign.CandidateDTO;
import com.egovernment.main.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.main.domain.dto.voteCampaign.VoteCampaignDTO;
import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.enums.CampaignType;
import com.egovernment.main.domain.enums.ElectionType;
import com.egovernment.main.repository.ElectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ElectionServiceTest {

    @Mock
    private ElectionRepository electionRepository;
    @Mock
    private  ModelMapper modelMapper;
    @Mock
    private CandidateService candidateService;

    private final String CAMPAIGN_TITLE = "Test Title";
    private final String CANDIDATE_NAME = "Test Name";
    private final String CANDIDATE_PARTY = "Test Name";
    private final int CANDIDATE_NUMBER = 1;
    private final Long ID = 1L;
    private final String ELECTION_TYPE_STRING = "PARLIAMENT";
    private final Campaign MOCK_CAMPAIGN = Campaign.builder()
            .campaignType(CampaignType.VOTING)
            .build();
    private Candidate TEST_CANDIDATE = Candidate.builder()
            .name(CANDIDATE_NAME)
            .candidateNumber(CANDIDATE_NUMBER)
            .party(CANDIDATE_PARTY)
            .build();
    private final Election MOCK_ELECTION =  Election.builder()
            .electionType(ElectionType.PARLIAMENT)
            .campaign(MOCK_CAMPAIGN)
            .candidateList(List.of(TEST_CANDIDATE))
            .build();

    @InjectMocks
    private ElectionService electionServiceToTest;

        @Test
    public void testInitSampleElections() {
        electionServiceToTest.initSampleElections(MOCK_CAMPAIGN);
        verify(electionRepository).save(ArgumentMatchers.any(Election.class));
    }

    @Test
    public void testLaunchElection() {
        Election result = electionServiceToTest.launchElection(ElectionType.PARLIAMENT, MOCK_CAMPAIGN);

        assertNotNull(result);
        assertEquals(ElectionType.PARLIAMENT, result.getElectionType());
        assertEquals(MOCK_CAMPAIGN, result.getCampaign());
    }

    @Test
    public void testGetElectionByIdFound() {
        when(electionRepository.findById(ID)).thenReturn(Optional.of(MOCK_ELECTION));

        Optional<Election> result = electionServiceToTest.getElectionById(ID);

        assertTrue(result.isPresent());
        assertEquals(MOCK_ELECTION, result.get());
        assertEquals(MOCK_ELECTION.getElectionType(), result.get().getElectionType());
    }

    @Test
    public void testGetElectionByIdNotFound() {
        when(electionRepository.findById(ID)).thenReturn(Optional.empty());

        Optional<Election> result = electionServiceToTest.getElectionById(ID);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetElectionByCampaignIdFound() {
        when(electionRepository.findByCampaignId(ID)).thenReturn(List.of(MOCK_ELECTION));

        List<Election> result = electionServiceToTest.getElectionsByCampaignId(ID);

        assertTrue(result.size() > 0);
        assertEquals(MOCK_ELECTION, result.get(0));
        assertEquals(MOCK_ELECTION.getElectionType(), result.get(0).getElectionType());

    }

    @Test
    public void testGetElectionByCampaignIdNotFound() {
        when(electionRepository.findByCampaignId(ID)).thenReturn(Collections.emptyList());

        List<Election> result = electionServiceToTest.getElectionsByCampaignId(ID);

        assertTrue(result.size() == 0);
    }

    @Test
    public void testGetCampaignElectionByIdReturnsTheRightResult(){
        CandidateDTO candidateDTO = CandidateDTO.builder()
                .candidateNumber(CANDIDATE_NUMBER)
                .candidateParty(CANDIDATE_PARTY)
                .candidateName(CANDIDATE_NAME)
                .build();

        VoteCampaignDTO voteCampaignDTO = VoteCampaignDTO.builder()
                .campaignType(CampaignType.VOTING.name())
                .electionType(ElectionType.PARLIAMENT.toString())
                .electionCandidates(List.of(candidateDTO))
                .build();

        when(this.electionRepository.findById(ID)).thenReturn(Optional.of(MOCK_ELECTION));
        when(this.modelMapper.map(any(Candidate.class), eq(CandidateDTO.class))).thenReturn(candidateDTO);

        VoteCampaignDTO result = this.electionServiceToTest.getCampaignElectionById(ID);

        assertNotNull(result);
        assertEquals(voteCampaignDTO.getCampaignType(), result.getCampaignType());
        assertEquals(voteCampaignDTO.getElectionType(), result.getElectionType());
        assertEquals(voteCampaignDTO.getElectionCandidates().size(), result.getElectionCandidates().size());
    }

    @Test
    public void testGetCampaignElectionByIdEmpty(){
        when(this.electionRepository.findById(ID)).thenReturn(Optional.empty());
        VoteCampaignDTO result = this.electionServiceToTest.getCampaignElectionById(ID);
        assertNull(result);
    }

}
