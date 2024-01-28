package com.egovernment.main.web.interfaces;

import com.egovernment.main.domain.dto.result.CampaignsOverviewDTO;
import com.egovernment.main.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.RESULTS_PATH)
public interface ResultControllerInterface {

    @GetMapping
    ResponseEntity<CampaignsOverviewDTO> getResults();
}