package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.voteCampaign.CandidateTemplateDTO;
import com.egovernment.egovbackend.domain.entity.Candidate;
import com.egovernment.egovbackend.domain.entity.Election;
import com.egovernment.egovbackend.domain.enums.ElectionType;
import com.egovernment.egovbackend.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {
    @Mock
    private  CandidateRepository candidateRepository;
    @Mock
    private  ElectionService electionService;
    @Mock
    private  ModelMapper modelMapper;
    private CandidateService candidateServiceToTest;
    private final String TEST_NAME = "Test Name";
    private final String TEST_PARTY = "Test Party";
    private final Long ID = 1L;

    @BeforeEach
    public void setUp() {
        candidateServiceToTest = new CandidateService(candidateRepository, electionService, modelMapper);
    }

    @Test
    public void testInitSampleCandidates() {
        Election testElection = Election.builder().electionType(ElectionType.PARLIAMENT).build();
        when(electionService.getElectionById(ID)).thenReturn(Optional.of(testElection));

        candidateServiceToTest.initSampleCandidates();

        verify(candidateRepository).saveAll(anyList());
    }

    @Test
    public void testCreateCandidate() {
        Election testElection = Election.builder().electionType(ElectionType.PARLIAMENT).build();

        Integer candidateNumber = 123;

        Candidate result = candidateServiceToTest.createCandidate(TEST_NAME, TEST_PARTY, testElection, candidateNumber);

        assertNotNull(result);
        assertEquals(TEST_NAME, result.getName());
        assertEquals(TEST_PARTY, result.getParty());
        assertEquals(candidateNumber, result.getCandidateNumber());
        assertEquals(testElection, result.getElection());
    }

    @Test
    public void testGetCandidateByIdReturnsTheRightCandidate() {
        Candidate mockCandidate = Candidate.builder()
                .name(TEST_NAME)
                .candidateNumber(123)
                .party(TEST_PARTY)
                .build();
        when(candidateRepository.findById(ID)).thenReturn(Optional.of(mockCandidate));

        Optional<Candidate> result = candidateServiceToTest.getCandidateById(ID);

        assertTrue(result.isPresent());
        assertEquals(mockCandidate.getName(), result.get().getName());
        assertEquals(mockCandidate.getParty(), result.get().getParty());
        assertEquals(mockCandidate.getCandidateNumber(), result.get().getCandidateNumber());
        assertEquals(mockCandidate, result.get());
    }

    @Test
    public void testGetCandidateByIdReturnsOptionalOfEmptyIfThereIsNoCandidateWithThatId() {
        when(candidateRepository.findById(ID)).thenReturn(Optional.empty());
        Optional<Candidate> result = candidateServiceToTest.getCandidateById(ID);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetCandidatesForElectionWithCandidates() {
        Candidate firstCandidate = Candidate.builder()
                .party("FirstTestCandidateParty")
                .name("FirstTest")
                .build();

        Candidate secondCandidate = Candidate.builder()
                .party("SecondTestCandidateParty")
                .name("SecondTest")
                .build();

        List<Candidate> candidateList = List.of(firstCandidate, secondCandidate);
        when(candidateRepository.findByElectionId(ID)).thenReturn(candidateList);

        CandidateTemplateDTO firstCandidateDTO = CandidateTemplateDTO.builder()
                .candidateName(firstCandidate.getName())
                .candidateParty(firstCandidate.getParty())
                .build();

        CandidateTemplateDTO secondCandidateDTO = CandidateTemplateDTO.builder()
                .candidateName(secondCandidate.getName())
                .candidateParty(secondCandidate.getParty())
                .build();

        when(modelMapper.map(firstCandidate, CandidateTemplateDTO.class))
                .thenReturn(firstCandidateDTO);

        when(modelMapper.map(secondCandidate, CandidateTemplateDTO.class))
                .thenReturn(secondCandidateDTO);


        List<CandidateTemplateDTO> result = candidateServiceToTest.getCandidatesForElection(ID);

        assertNotNull(result);
        assertEquals(candidateList.size(), result.size());

        assertEquals(firstCandidate.getName(), result.get(0).getCandidateName());
        assertEquals(firstCandidate.getParty(), result.get(0).getCandidateParty());

        assertEquals(secondCandidate.getName(), result.get(1).getCandidateName());
        assertEquals(secondCandidate.getParty(), result.get(1).getCandidateParty());
    }

    @Test
    void testCreateCandidatesCreatesTheRightListOfCandidates(){

        Election election = Election.builder().electionType(ElectionType.PARLIAMENT).build();

        CandidateTemplateDTO candidateTemplateDTO = CandidateTemplateDTO.builder()
                .candidateName(TEST_NAME)
                .candidateParty(TEST_PARTY)
                .build();

        Candidate candidate = Candidate.builder()
                .name(TEST_NAME)
                .party(TEST_PARTY)
                .build();

        when(modelMapper.map(candidateTemplateDTO, Candidate.class)).thenReturn(candidate);
        this.candidateServiceToTest.createCandidates(List.of(candidateTemplateDTO), election);

        verify(candidateRepository, atLeast(1)).saveAll(anyList());

    }


}
