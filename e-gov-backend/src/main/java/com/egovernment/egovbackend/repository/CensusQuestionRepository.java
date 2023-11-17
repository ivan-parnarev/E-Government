package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.CensusQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CensusQuestionRepository extends JpaRepository<CensusQuestion, Long> {

    List<CensusQuestion> findAllByCampaignId(Long campaignId);
}
