package com.egovernment.egovbackend.web.interfaces;

import com.egovernment.egovbackend.domain.dto.CensusDTO;
import com.egovernment.egovbackend.web.path.ApiPaths;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.CENSUS_PATH)
public interface CensusControllerInterface {

    ResponseEntity<CensusDTO> saveUserCensusData(@Valid @RequestBody CensusDTO census);

}
