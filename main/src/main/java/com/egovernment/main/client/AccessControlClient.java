package com.egovernment.egovbackend.client;

import com.egovernment.egovbackend.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.egovbackend.web.path.ApiPaths;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "access-control-client",  url = "${spring.cloud.openfeign.client.config.access-control-client.url}")
public interface AccessControlClient {

    @GetMapping(ApiPaths.CAMPAIGN_PATH + ApiPaths.ACTIVE_PATH)
    ResponseEntity<List<CampaignFilteredDTO>> getActiveCampaigns();

    @GetMapping(ApiPaths.CAMPAIGN_PATH + ApiPaths.ACTIVE_PATH)
    ResponseEntity<List<CampaignFilteredDTO>> getActiveCampaignsNew(@RequestParam String region);

}
