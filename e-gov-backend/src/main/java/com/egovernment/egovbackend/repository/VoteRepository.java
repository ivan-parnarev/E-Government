package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Vote v WHERE v.userPin = :userPin AND v.campaign.id = :campaignId")
    boolean existsByUserPinAndCampaignId(@Param("userPin") String userPin, @Param("campaignId") Long campaignId);
}
