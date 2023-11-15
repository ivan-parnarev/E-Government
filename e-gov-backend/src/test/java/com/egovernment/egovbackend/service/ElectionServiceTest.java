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
                .title("Test Title")
                .build();

        Election result = electionServiceToTest.launchElection(ElectionType.PARLIAMENT, mockCampaign);

        assertNotNull(result);
        assertEquals(ElectionType.PARLIAMENT, result.getElectionType());
        assertEquals(mockCampaign, result.getCampaign());
    }

    @Test
    public void testGetElectionByIdFound() {
        long electionId = 1L;
        Election mockElection = Election.builder()
                .electionType(ElectionType.PARLIAMENT)
                .build();

        when(electionRepository.findById(electionId)).thenReturn(Optional.of(mockElection));

        Optional<Election> result = electionServiceToTest.getElectionById(electionId);

        assertTrue(result.isPresent());
        assertEquals(mockElection, result.get());
        assertEquals(mockElection.getElectionType(), result.get().getElectionType());
    }

    @Test
    public void testGetElectionByIdNotFound() {
        long electionId = 1L;
        when(electionRepository.findById(electionId)).thenReturn(Optional.empty());

        Optional<Election> result = electionServiceToTest.getElectionById(electionId);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetElectionByCampaignIdFound() {
        Long campaignId = 1L;

        Election mockElection = Election.builder()
                .electionType(ElectionType.PARLIAMENT)
                .build();
        when(electionRepository.findByCampaignId(campaignId)).thenReturn(Optional.of(mockElection));

        Optional<Election> result = electionServiceToTest.getElectionByCampaignId(campaignId);

        assertTrue(result.isPresent());
        assertEquals(mockElection, result.get());
        assertEquals(mockElection.getElectionType(), result.get().getElectionType());

    }

    @Test
    public void testGetElectionByCampaignIdNotFound() {
        Long campaignId = 1L;
        when(electionRepository.findByCampaignId(campaignId)).thenReturn(Optional.empty());

        Optional<Election> result = electionServiceToTest.getElectionByCampaignId(campaignId);

        assertFalse(result.isPresent());
    }



}
