package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    @Query("SELECT CASE WHEN COUNT(answer) > 0 THEN true ELSE false END FROM UserAnswer answer WHERE answer.user.PIN = :userPin AND answer.campaign.id = :campaignId")
    boolean censusExistsByUserPinAndCampaignId(@Param("userPin") String userPin, @Param("campaignId") Long campaignId);
}
