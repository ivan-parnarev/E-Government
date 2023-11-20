package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    Optional<Election> findByCampaignId(Long campaignId);
}
