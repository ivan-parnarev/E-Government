package com.egovernment.main.client;

import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
import com.egovernment.main.web.path.ApiPaths;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "access-control-client",  url = "${spring.cloud.openfeign.client.config.access-control-client.url}")
public interface AccessControlClient {

    @GetMapping(ApiPaths.CAMPAIGN_PATH + ApiPaths.ACTIVE_PATH)
    ResponseEntity<List<CampaignFilteredDTO>> getActiveCampaigns(String regionName);

}