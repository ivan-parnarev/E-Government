package com.egovernment.authentication.repository;

import com.egovernment.authentication.domain.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByName(String regionName);
}
