package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.enums.CampaignType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> getAllByCampaignType(CampaignType campaignType);
}
