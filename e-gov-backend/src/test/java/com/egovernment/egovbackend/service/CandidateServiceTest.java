package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.CandidateTemplateDTO;
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

    @BeforeEach
    public void setUp() {
        candidateServiceToTest = new CandidateService(candidateRepository, electionService, modelMapper);
    }

    @Test
    public void testInitSampleCandidates() {
        Election testElection = Election.builder().electionType(ElectionType.PARLIAMENT).build();
        when(electionService.getElectionById(1L)).thenReturn(Optional.of(testElection));

        candidateServiceToTest.initSampleCandidates();

        verify(candidateRepository).saveAll(anyList());
    }

    @Test
    public void testCreateCandidate() {
        Election testElection = Election.builder().electionType(ElectionType.PARLIAMENT).build();
        String name = "Test Name";
        String party = "Test Party";
        Integer candidateNumber = 123;

        Candidate result = candidateServiceToTest.createCandidate(name, party, testElection, candidateNumber);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(party, result.getParty());
        assertEquals(candidateNumber, result.getCandidateNumber());
        assertEquals(testElection, result.getElection());
    }

    @Test
    public void testGetCandidateByIdReturnsTheRightCandidate() {
        Long candidateId = 1L;
        Candidate mockCandidate = Candidate.builder()
                .name("TestName")
                .candidateNumber(123)
                .party("TestParty")
                .build();
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(mockCandidate));

        Optional<Candidate> result = candidateServiceToTest.getCandidateById(candidateId);

        assertTrue(result.isPresent());
        assertEquals(mockCandidate.getName(), result.get().getName());
        assertEquals(mockCandidate.getParty(), result.get().getParty());
        assertEquals(mockCandidate.getCandidateNumber(), result.get().getCandidateNumber());
        assertEquals(mockCandidate, result.get());
    }

    @Test
    public void testGetCandidateByIdReturnsOptionalOfEmptyIfThereIsNoCandidateWithThatId() {
        Long candidateId = 1L;
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());
        Optional<Candidate> result = candidateServiceToTest.getCandidateById(candidateId);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetCandidatesForElectionWithCandidates() {
        Long electionId = 1L;
        Candidate firstCandidate = Candidate.builder()
                .party("FirstTestCandidateParty")
                .name("FirstTest")
                .build();

        Candidate secondCandidate = Candidate.builder()
                .party("SecondTestCandidateParty")
                .name("SecondTest")
                .build();

        List<Candidate> candidateList = List.of(firstCandidate, secondCandidate);
        when(candidateRepository.findByElectionId(electionId)).thenReturn(candidateList);

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


        List<CandidateTemplateDTO> result = candidateServiceToTest.getCandidatesForElection(electionId);

        assertNotNull(result);
        assertEquals(candidateList.size(), result.size());

        assertEquals(firstCandidate.getName(), result.get(0).getCandidateName());
        assertEquals(firstCandidate.getParty(), result.get(0).getCandidateParty());

        assertEquals(secondCandidate.getName(), result.get(1).getCandidateName());
        assertEquals(secondCandidate.getParty(), result.get(1).getCandidateParty());
    }


}
