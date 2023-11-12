package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.Census;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CensusRepository extends JpaRepository<Census, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Census c WHERE c.userPin = :userPin AND c.campaign.id = :campaignId")
    boolean censusExistsByUserPinAndCampaignId(@Param("userPin") String userPin, @Param("campaignId") Long campaignId);
}
