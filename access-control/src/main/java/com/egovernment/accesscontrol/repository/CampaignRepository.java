package com.egovernment.accesscontrol.repository;

import com.egovernment.accesscontrol.domain.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

}

