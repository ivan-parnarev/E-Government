package com.egovernment.authentication.repository;

import com.egovernment.authentication.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String cityName);
}
