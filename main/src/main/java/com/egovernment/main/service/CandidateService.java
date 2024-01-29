package com.egovernment.main.service;

import com.egovernment.main.domain.dto.voteCampaign.CandidateDTO;
import com.egovernment.main.domain.dto.voteCampaign.CandidateTemplateDTO;
import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.domain.factory.candidate.CandidateFactory;
import com.egovernment.main.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final ElectionService electionService;
    private final ModelMapper modelMapper;
    private final CandidateFactory candidateFactory = new CandidateFactory();

    public CandidateService(@Lazy ElectionService electionService,
                            CandidateRepository candidateRepository,
                            ModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.electionService = electionService;
        this.modelMapper = modelMapper;
    }

    public void initSampleCandidates() {
        if(this.candidateRepository.count() == 0){
            Optional<Election> election = this.electionService.getElectionById(1L);
            if (election.isPresent()) {
                Candidate candidate = this.createCandidate("ГЕРБ", "ГЕРБ", election.get(), 11);
                Candidate candidate1 = this.createCandidate("ПП", "ПП", election.get(), 12);
                Candidate candidate2 = this.createCandidate("БЗНС", "БЗНС", election.get(), 13);

                this.candidateRepository.saveAll(List.of(candidate, candidate1, candidate2));
            }
        }
    }

    public Candidate createCandidate(String name, String party, Election election, Integer candidateNumber) {
        return this.candidateFactory.createCandidate(name, party, election, candidateNumber);
    }

    public List<CandidateTemplateDTO> getCandidatesForElection(Long electionId) {
            return this.candidateRepository
                    .findByElectionId(electionId)
                    .stream()
                    .map(c -> this.modelMapper.map(c, CandidateTemplateDTO.class))
                    .collect(Collectors.toList());
    }

    public Optional<Candidate> getCandidateById(Long candidateId) {
        return this.candidateRepository.findById(candidateId);
    }

    public void createCandidates(List<CandidateTemplateDTO> candidatesList, Election election) {
        List<Candidate> candidates = candidatesList
                .stream()
                .map(c -> this.modelMapper.map(c, Candidate.class))
                .peek(c -> c.setElection(election))
                .toList();
        this.candidateRepository.saveAll(candidates);

    }

    public Candidate mapCandidateDTOtoCandidate(CandidateDTO candidateDTO) {
        return Candidate.builder()
                .name(candidateDTO.getCandidateName())
                .party(candidateDTO.getCandidateParty())
                .candidateNumber(candidateDTO.getCandidateNumber())
                .build();
    }

    public List<Candidate> saveCandidates(List<Candidate> candidates) {
        return this.candidateRepository.saveAllAndFlush(candidates);
    }
}
