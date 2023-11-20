package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByElectionId(Long electionId);
}
