package com.egovernment.main.service;

import com.egovernment.main.domain.entity.Campaign;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ElectionServiceTest {

    @Mock
    private ElectionRepository electionRepository;

    @Mock
    private CandidateService candidateService;

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
        assertEquals(MOCK_ELECTION, result.get(1));
        assertEquals(MOCK_ELECTION.getElectionType(), result.get(1).getElectionType());

    }

    @Test
    public void testGetElectionByCampaignIdNotFound() {
        when(electionRepository.findByCampaignId(ID)).thenReturn(Collections.emptyList());

        List<Election> result = electionServiceToTest.getElectionsByCampaignId(ID);

        assertTrue(result.size() == 0);
    }
}
