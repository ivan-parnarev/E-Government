package com.egovernment.accesscontrol.repository;

import com.egovernment.accesscontrol.domain.entity.FilteredCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilteredCampaignRepository extends JpaRepository<FilteredCampaign, Long> {
}
