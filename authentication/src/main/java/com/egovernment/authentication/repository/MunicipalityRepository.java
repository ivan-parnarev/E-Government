package com.egovernment.authentication.repository;

import com.egovernment.authentication.domain.entity.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    Municipality findByName(String municipalityName);
}
