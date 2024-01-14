package com.egovernment.main.domain.factory.candidate;

import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;

public class CandidateFactory implements CandidateFactoryInterface{
    @Override
    public Candidate createCandidate(String name, String party, Election election, Integer candidateNumber) {
        return Candidate.builder()
                .name(name)
                .party(party)
                .election(election)
                .candidateNumber(candidateNumber)
                .build();
    }
}
