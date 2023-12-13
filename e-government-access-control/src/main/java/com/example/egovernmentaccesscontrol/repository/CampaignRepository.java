package com.example.egovernmentaccesscontrol.repository;

import com.example.egovernmentaccesscontrol.domain.Campaign;
import com.example.egovernmentaccesscontrol.enums.CampaignType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> getAllByCampaignType(CampaignType campaignType);
}

