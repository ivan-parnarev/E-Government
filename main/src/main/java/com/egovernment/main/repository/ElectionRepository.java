package com.egovernment.main.repository;

import com.egovernment.main.domain.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    List<Election> findByCampaignId(Long campaignId);
}
