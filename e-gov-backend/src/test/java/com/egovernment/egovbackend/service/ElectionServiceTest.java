package com.egovernment.egovbackend.service;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ElectionServiceTest {

    @Mock
    private ElectionRepository electionRepository;

    private ElectionService electionServiceToTest;
    private final String CAMPAIGN_TITLE = "Test Title";
    private final Long ID = 1L;

    @BeforeEach
    public void setUp() {
        electionServiceToTest = new ElectionService(electionRepository);
    }

        @Test
    public void testInitSampleElections() {
        Campaign mockCampaign = Campaign.builder()
                .campaignType(CampaignType.VOTING)
                .build();

        electionServiceToTest.initSampleElections(mockCampaign);
        verify(electionRepository).save(ArgumentMatchers.any(Election.class));
    }

    @Test
    public void testLaunchElection() {
        Campaign mockCampaign = Campaign.builder()
                .title(CAMPAIGN_TITLE)
                .build();

        Election result = electionServiceToTest.launchElection(ElectionType.PARLIAMENT, mockCampaign);

        assertNotNull(result);
        assertEquals(ElectionType.PARLIAMENT, result.getElectionType());
        assertEquals(mockCampaign, result.getCampaign());
    }

    @Test
    public void testGetElectionByIdFound() {

        Election mockElection = Election.builder()
                .electionType(ElectionType.PARLIAMENT)
                .build();

        when(electionRepository.findById(ID)).thenReturn(Optional.of(mockElection));

        Optional<Election> result = electionServiceToTest.getElectionById(ID);

        assertTrue(result.isPresent());
        assertEquals(mockElection, result.get());
        assertEquals(mockElection.getElectionType(), result.get().getElectionType());
    }

    @Test
    public void testGetElectionByIdNotFound() {
        when(electionRepository.findById(ID)).thenReturn(Optional.empty());

        Optional<Election> result = electionServiceToTest.getElectionById(ID);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetElectionByCampaignIdFound() {

        Election mockElection = Election.builder()
                .electionType(ElectionType.PARLIAMENT)
                .build();
        when(electionRepository.findByCampaignId(ID)).thenReturn(Optional.of(mockElection));

        Optional<Election> result = electionServiceToTest.getElectionByCampaignId(ID);

        assertTrue(result.isPresent());
        assertEquals(mockElection, result.get());
        assertEquals(mockElection.getElectionType(), result.get().getElectionType());

    }

    @Test
    public void testGetElectionByCampaignIdNotFound() {
        when(electionRepository.findByCampaignId(ID)).thenReturn(Optional.empty());

        Optional<Election> result = electionServiceToTest.getElectionByCampaignId(ID);

        assertFalse(result.isPresent());
    }



}
