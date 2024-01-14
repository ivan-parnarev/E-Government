package com.example.accesscontrol.web.interfaces;

import com.example.accesscontrol.domain.dto.CampaignFilteredDTO;
import com.example.accesscontrol.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.CAMPAIGN_PATH)
public interface CampaignControllerInterface {

    ResponseEntity<List<CampaignFilteredDTO>> getActiveCampaigns();

}
