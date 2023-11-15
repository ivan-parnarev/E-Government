package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.CensusAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CensusAnswerRepository extends JpaRepository<CensusAnswer, Long> {

    @Query("SELECT CASE WHEN COUNT(answer) > 0 THEN true ELSE false END FROM CensusAnswer answer WHERE answer.user.PIN = :userPin AND answer.censusQuestion.campaign = :campaignId")
    boolean censusExistsByUserIdAndCampaignId(@Param("userPin") String userPin, @Param("campaignId") Long campaignId);
}
