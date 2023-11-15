package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.CensusQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CensusQuestionRepository extends JpaRepository<CensusQuestion, Long> {

}
