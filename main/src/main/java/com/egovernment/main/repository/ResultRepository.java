package com.egovernment.main.repository;

import com.egovernment.main.domain.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findByElectionIdAndCandidateId(Long electionId, Long candidateId);

}