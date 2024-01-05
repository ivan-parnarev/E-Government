package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.voteCampaign.CandidateTemplateDTO;
import com.egovernment.egovbackend.domain.dto.voteCampaign.CreateVotingCampaignDTO;
import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.enums.CampaignType;
import com.egovernment.egovbackend.domain.enums.ElectionType;
import com.egovernment.egovbackend.repository.ElectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ElectionServiceTest {

    @Mock
    private ElectionRepository electionRepository;

    private ElectionService electionServiceToTest;
    private final String CAMPAIGN_TITLE = "Test Title";
    private final String CANDIDATE_NAME = "Test Name";
    private final Long ID = 1L;
    private final String ELECTION_TYPE_STRING = "PARLIAMENT";
    private final Campaign MOCK_CAMPAIGN = Campaign.builder()
            .campaignType(CampaignType.VOTING)
            .build();
    private final Election MOCK_ELECTION =  Election.builder()
            .electionType(ElectionType.PARLIAMENT)
            .campaign(MOCK_CAMPAIGN)
            .build();

    @BeforeEach
    public void setUp() {
        electionServiceToTest = new ElectionService(electionRepository);
    }

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
        when(electionRepository.findByCampaignId(ID)).thenReturn(Optional.of(MOCK_ELECTION));

        Optional<Election> result = electionServiceToTest.getElectionByCampaignId(ID);

        assertTrue(result.isPresent());
        assertEquals(MOCK_ELECTION, result.get());
        assertEquals(MOCK_ELECTION.getElectionType(), result.get().getElectionType());

    }

    @Test
    public void testGetElectionByCampaignIdNotFound() {
        when(electionRepository.findByCampaignId(ID)).thenReturn(Optional.empty());

        Optional<Election> result = electionServiceToTest.getElectionByCampaignId(ID);

        assertFalse(result.isPresent());
    }

//    @Test
//    public void testCreateElectionLaunchesElection() {
//        CandidateTemplateDTO candidateTemplateDTO = CandidateTemplateDTO
//                .builder()
//                .candidateName(CANDIDATE_NAME)
//                .build();
//
//        CreateVotingCampaignDTO createVotingCampaignDTO = CreateVotingCampaignDTO.builder()
//                .electionType(ELECTION_TYPE_STRING)
//                .candidates(List.of(candidateTemplateDTO))
//                .build();
//
//        Election result = this.electionServiceToTest.createElection(createVotingCampaignDTO.getElectionType(), MOCK_CAMPAIGN);
//        verify(electionRepository).save(any(Election.class));
//
//        assertNotNull(result);
//        assertEquals(MOCK_ELECTION.getElectionType(), result.getElectionType());
//        assertEquals(MOCK_ELECTION.getCampaign().getTitle(), result.getCampaign().getTitle());
//
//    }

}
