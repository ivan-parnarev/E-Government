package com.egovernment.main.repository;

import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.enums.CampaignType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> getAllByCampaignType(CampaignType campaignType);
}
