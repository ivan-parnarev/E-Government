package com.egovernment.egovbackend.domain.factory.candidate;

import com.egovernment.egovbackend.domain.entity.Candidate;
import com.egovernment.egovbackend.domain.entity.Election;

public interface CandidateFactoryInterface {
    Candidate createCandidate(String name, String party, Election election, Integer candidateNumber);

}
