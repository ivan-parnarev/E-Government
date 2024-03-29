package com.egovernment.main.web;

import com.egovernment.main.domain.dto.region.RegionDTO;
import com.egovernment.main.service.RegionService;
import com.egovernment.main.web.interfaces.RegionControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegionController implements RegionControllerInterface {

    private final RegionService regionService;

    @Override
    @GetMapping
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }

}
