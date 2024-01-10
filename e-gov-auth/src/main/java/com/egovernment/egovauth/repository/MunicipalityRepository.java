package com.egovernment.egovauth.repository;

import com.egovernment.egovauth.domain.entity.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    Municipality findByName(String municipalityName);
}
