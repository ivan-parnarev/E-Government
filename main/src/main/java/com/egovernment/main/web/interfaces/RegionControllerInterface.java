package com.egovernment.main.web.interfaces;

import com.egovernment.main.domain.dto.region.RegionDTO;
import com.egovernment.main.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.REGIONS_PATH)
public interface RegionControllerInterface {
    ResponseEntity<List<RegionDTO>> getAllRegions();
}
