package com.egovernment.main.domain.factory.candidate;

import com.egovernment.main.domain.entity.Candidate;
import com.egovernment.main.domain.entity.Election;

public interface CandidateFactoryInterface {
    Candidate createCandidate(String name, String party, Election election, Integer candidateNumber);

}
