package com.egovernment.main.repository;

import com.egovernment.main.domain.entity.CensusQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CensusQuestionRepository extends JpaRepository<CensusQuestion, Long> {

    List<CensusQuestion> findAllByCampaignId(Long campaignId);

    Optional<CensusQuestion> findByQuestionHashedText(String hashText);
}
